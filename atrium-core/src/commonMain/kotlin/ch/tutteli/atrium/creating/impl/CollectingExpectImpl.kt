package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.FeatureProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroupWithDesignation
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableWithDesignation
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages
import ch.tutteli.kbox.ifNotEmpty

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class CollectingExpectImpl<T>(
    maybeSubject: Option<T>,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), CollectingExpect<T> {
    private val proofs = mutableListOf<Proof>()

    @Suppress("DEPRECATION")
    @Deprecated(
        "Assertion is deprecated, move to Proof and use getCollectedProofs instead. Will be removed with 2.0.0 at the latest",
        replaceWith = ReplaceWith("this.getCollectedProofs()")
    )
    override fun getAssertions(): List<Assertion> =
        mapProofsToAssertion(proofs)

    @Suppress("DEPRECATION")
    private fun mapProofsToAssertion(proofs: List<Proof>): List<Assertion> =
        proofs.map { proof ->
            if (proof is Assertion) proof
            else when (proof) {
                is ProofGroupWithDesignation -> BasicAssertionGroup(
                    if (proof is FeatureProofGroup) DefaultFeatureAssertionGroupType else DefaultListAssertionGroupType,
                    ch.tutteli.atrium.reporting.translating.Untranslatable(proof.description.toString()),
                    proof.representation,
                    mapProofsToAssertion(proof.children.filterIsInstance<Proof>())
                )

                is ReportableWithDesignation -> BasicDescriptiveAssertion(
                    ch.tutteli.atrium.reporting.translating.Untranslatable(proof.description.toString()),
                    proof.description
                ) { proof.holds() }

                else -> BasicDescriptiveAssertion(
                    ch.tutteli.atrium.reporting.translating.Untranslatable("❗❗ Assertion is deprecated, move to Proof, cannot show description"),
                    proof
                ) { proof.holds() }
            }
        }

    override fun getCollectedProofs(): List<Proof> = proofs.toList()

    override fun append(proof: Proof): Expect<T> = apply {
        proofs.add(proof)
    }

    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        replaceWith = ReplaceWith(
            "this.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the generic */ ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED))).first",
            "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
            "ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages"
        )
    )
    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T> {
        @Suppress("DEPRECATION")
        return super.appendAsGroup(assertionCreator) as CollectingExpect<T>
    }

    override fun appendAsGroupIndicateIfOneCollected(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<T>
    ): Pair<CollectingExpect<T>, Boolean> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allProofs = mutableListOf<Proof>()
        allProofs.addAll(getCollectedProofs())
        proofs.clear()

        //TODO 1.3.0 collect unexpected exceptions, move DefaultExceptionHandler to core
        expectationCreatorWithUsageHints.expectationCreator(this)
        val newProofs = getCollectedProofs()
        val atLeastOneCollected = newProofs.isNotEmpty()
        proofs.clear()

        if (atLeastOneCollected) {
            allProofs.addAll(newProofs)
        } else {
            allProofs.add(
                buildProof {
                    fixedClaimGroup(
                        ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED, false,
                        holds = false,
                    ) {
                        simpleProof(DescriptionAnyProof.TO_EQUAL, true) { false }

                        addAll(
                            expectationCreatorWithUsageHints.usageHintsOverloadWithoutExpectationCreator.ifNotEmpty { hints ->
                                listOf(
                                    Reportable.usageHintGroup(listOf(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION) + hints)
                                )
                            }
                        )
                    }
                }
            )
        }
        allProofs.forEach { append(it) }

        return Pair(this, atLeastOneCollected)
    }
}
