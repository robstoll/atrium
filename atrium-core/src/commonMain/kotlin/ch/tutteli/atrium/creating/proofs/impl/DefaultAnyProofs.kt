package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium._core
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.*
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.creating.transformers.SubjectChanger
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.forgotToAppendProofPseudoUsageHint
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof.*
import kotlin.reflect.KClass

class DefaultAnyProofs : AnyProofs {
    override fun <T> toEqual(container: ProofContainer<T>, expected: T): Proof =
        container.buildSimpleProof(TO_EQUAL, expected) { it == expected }

    override fun <T> notToEqual(container: ProofContainer<T>, expected: T): Proof =
        container.buildSimpleProof(NOT_TO_EQUAL, expected) { it != expected }

    override fun <T> toBeTheInstance(container: ProofContainer<T>, expected: T): Proof =
        container.buildSimpleProof(TO_BE_THE_INSTANCE, expected) { it === expected }

    override fun <T> notToBeTheInstance(container: ProofContainer<T>, expected: T): Proof =
        container.buildSimpleProof(NOT_TO_BE_THE_INSTANCE, expected) { it !== expected }

    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    override fun <T, SubTypeOfT> toBeAnInstanceOf(
        container: ProofContainer<T>,
        subType: KClass<SubTypeOfT>
    ): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T =
        container.changeSubject.reportBuilder()
            .downCastTo(subType)
            .build()

    override fun <T : Any> notToEqualNullButToBeAnInstanceOf(
        container: ProofContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T> = container.changeSubject.reportBuilder()
        .withDescriptionAndRepresentation(description = NOT_TO_EQUAL, representation = Text.NULL)
        .withTransformation {
            Option.someIf(subType.isInstance(it)) { subType.cast(it) }
        }
        .withFailureHandler(NotToEqualNullFailureHandler(subType))
        .build()

    override fun <T> notToEqualOneIn(container: ProofContainer<T>, expected: Iterable<T>): Proof =
        container.buildProof {
            proofGroup(NOT_TO_EQUAL_ONE_OF, Text.EMPTY) {
                expected.forEach { value ->
                    representationOnlyProof(value) { it != value }
                }
            }
        }
}

internal class NotToEqualNullFailureHandler<T : Any>(
    private val subType: KClass<T>
) : SubjectChanger.FailureHandler<T?, T> {

    override fun createProof(
        container: ProofContainer<T?>,
        proof: Proof,
        maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<T>>
    ): Proof = container.buildProof {
        add(proof)
        collect(
            ExpectationCreatorWithUsageHints(forgotToAppendProofPseudoUsageHint) {
                // we already know that we cannot transform as we are in the failure handler
                val expectAfterTheoreticalTransformation = _core.toBeAnInstanceOf(subType)
                maybeExpectationCreatorWithUsageHints.fold({
                    expectAfterTheoreticalTransformation.transform()
                }, {
                    expectAfterTheoreticalTransformation.transformAndAppend(it)
                })
            }
        )

    }
}
