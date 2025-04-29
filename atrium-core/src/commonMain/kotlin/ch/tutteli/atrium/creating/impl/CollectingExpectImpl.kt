package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.FeatureProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.ProofGroupWithDesignation
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.impl.DefaultInvisibleProofGroup
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.*
import ch.tutteli.atrium.reporting.reportables.ErrorMessages
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof

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
        mapReportablesToAssertions(proofs)

    @Suppress("DEPRECATION")
    private fun mapReportablesToAssertions(reportables: List<Reportable>): List<Assertion> =
        reportables.map { reportable ->
            when (reportable) {
                is Assertion -> reportable
                is Proof -> mapProofToAssertion(reportable)

                is ProofExplanation -> ExplanatoryAssertionGroup(
                    DefaultExplanatoryAssertionGroupType,
                    mapReportablesToAssertions(reportable.children),
                    holds = false
                )

                is UsageHintGroup -> ExplanatoryAssertionGroup(
                    HintAssertionGroupType,
                    mapReportablesToAssertions(reportable.children),
                    holds = false
                )

                is DebugGroup -> ExplanatoryAssertionGroup(
                    InformationAssertionGroupType(withIndent = true),
                    listOf(
                        BasicAssertionGroup(
                            DefaultListAssertionGroupType,
                            descriptionToUntranslatable(reportable.description),
                            Text.EMPTY,
                            mapReportablesToAssertions(reportable.children)
                        )
                    ),
                    holds = false
                )

                is InformationGroup -> ExplanatoryAssertionGroup(
                    InformationAssertionGroupType(withIndent = true),
                    listOf(
                        BasicAssertionGroup(
                            DefaultListAssertionGroupType,
                            descriptionToUntranslatable(reportable.description),
                            Text.EMPTY,
                            mapReportablesToAssertions(reportable.children)
                        )
                    ),
                    holds = false
                )

                is FailureExplanationGroup -> ExplanatoryAssertionGroup(
                    WarningAssertionGroupType,
                    listOf(
                        BasicAssertionGroup(
                            DefaultListAssertionGroupType,
                            descriptionToUntranslatable(reportable.description),
                            Text.EMPTY,
                            mapReportablesToAssertions(reportable.children)
                        )
                    ),
                    holds = false
                )

                is ReportableWithDesignation -> BasicDescriptiveAssertion(
                    descriptionToUntranslatable(reportable.description),
                    reportable.representation
                ) { true }

                else -> BasicDescriptiveAssertion(
                    ch.tutteli.atrium.reporting.translating.Untranslatable("❗❗ Assertion is deprecated, move to Proof, cannot show description"),
                    reportable
                ) { true }
            }
        }

    @Suppress("DEPRECATION")
    private fun mapProofToAssertion(proof: Proof): Assertion = when (proof) {

        is DefaultInvisibleProofGroup -> InvisibleAssertionGroup(mapReportablesToAssertions(proof.children))

        is ProofGroupWithDesignation -> BasicAssertionGroup(
            if (proof is FeatureProofGroup) DefaultFeatureAssertionGroupType else DefaultListAssertionGroupType,
            descriptionToUntranslatable(proof.description),
            proof.representation,
            mapReportablesToAssertions(proof.children)
        )

        is ProofGroup -> BasicAssertionGroup(
            DefaultListAssertionGroupType,
            ch.tutteli.atrium.reporting.translating.Untranslatable("❗❗ Assertion is deprecated, move to Proof, cannot show description"),
            proof,
            mapReportablesToAssertions(proof.children),
        )

        is ReportableWithDesignation -> BasicDescriptiveAssertion(
            descriptionToUntranslatable(proof.description),
            proof.representation
        ) { proof.holds() }

        else -> BasicDescriptiveAssertion(
            ch.tutteli.atrium.reporting.translating.Untranslatable("❗❗ Assertion is deprecated, move to Proof, cannot show description"),
            proof
        ) { proof.holds() }
    }

    @Suppress("DEPRECATION")
    private fun descriptionToUntranslatable(description: Diagnostic) =
        ch.tutteli.atrium.reporting.translating.Untranslatable(
            (description as? TextElement)?.string ?: description.toString()
        )

    override fun getCollectedProofs(): List<Proof> = proofs.toList()

    override fun append(proof: Proof): Expect<T> = apply {
        proofs.add(proof)
    }

    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        replaceWith = ReplaceWith(
            "this.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */))).first",
            "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
            "ch.tutteli.atrium.reporting.reportables.defaultHintsAtLeastOneExpectationDefined"
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

                        //TODO 1.3.0 introduce usageErrorGroup which uses an exploding icon and only intend
                        usageHintGroup {
                            add(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION)
                            addAll(expectationCreatorWithUsageHints.usageHintsAlternativeWithoutExpectationCreator)
                        }
                    }
                }
            )
        }
        allProofs.forEach { append(it) }

        return Pair(this, atLeastOneCollected)
    }
}
