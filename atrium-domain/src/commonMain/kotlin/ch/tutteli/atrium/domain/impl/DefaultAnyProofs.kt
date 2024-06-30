package ch.tutteli.atrium.domain.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.*
import ch.tutteli.atrium.domain.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.reporting.buildProof
import ch.tutteli.atrium.domain.reporting.buildSimpleProof
import ch.tutteli.atrium.reporting.Text
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


    override fun <T> notToEqualOneIn(container: ProofContainer<T>, expected: Iterable<T>): Proof =
        container.buildProof {
            group(NOT_TO_EQUAL_ONE_OF, Text.EMPTY) {
                expected.forEach { value ->
                    representationOnlyProof(value) { it != value }
                }
            }
        }
}
