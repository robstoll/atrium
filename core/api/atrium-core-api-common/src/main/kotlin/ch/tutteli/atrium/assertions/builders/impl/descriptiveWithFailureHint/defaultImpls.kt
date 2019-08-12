package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.reporting.translating.Translatable


internal class FailureHintSubjectDefinedOptionImpl<T> :
    DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Assertion): DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption.create(failureHintFactory)
}

internal class FailureHintSubjectAbsentOptionImpl<T>(
    override val ifDefined: (T) -> Assertion
) : DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T>


internal class ShowOptionImpl(
    private val test: () -> Boolean,
    private val failureHintFactory: () -> Assertion
) : DescriptiveAssertionWithFailureHint.ShowOption {

    override val showForAnyFailure: Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> =
        createDescriptiveLikeAssertionDescriptionOption(trueProvider)

    override fun showOnlyIf(
        predicate: () -> Boolean
    ): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> =
        createDescriptiveLikeAssertionDescriptionOption(predicate)

    private fun createDescriptiveLikeAssertionDescriptionOption(
        predicate: () -> Boolean
    ): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> =
        Descriptive.DescriptionOption.create(
            test,
            { t, d, r -> DescriptiveAssertionWithFailureHint.FinalStep.create(t, predicate, failureHintFactory, d, r) }
        )
}


internal class ShowSubjectDefinedOptionImpl<T> : DescriptiveAssertionWithFailureHint.ShowSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Boolean): DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption.create(failureHintFactory)
}

internal class ShowSubjectAbsentOptionImpl<T>(
    override val ifDefined: (T) -> Boolean
) : DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T>


internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val showHint: () -> Boolean,
    override val failureHintFactory: () -> Assertion,
    override val description: Translatable,
    override val representation: Any
) : DescriptiveAssertionWithFailureHint.FinalStep {

    override fun build(): Assertion {
        //TODO remove try catch with 1.0.0, should no longer be necessary
        val holds = try {
            test()
        } catch (@Suppress("DEPRECATION") e: ch.tutteli.atrium.creating.PlantHasNoSubjectException) {
            // failure hint does not need to be shown if plant is absent
            false
        }
        return if (holds || !showHint()) {
            assertionBuilder.createDescriptive(description, representation) { holds }
        } else {
            assertionBuilder.fixedClaimGroup
                .withListType
                .failing
                .withDescriptionAndRepresentation(description, representation)
                .withAssertion(failureHintFactory())
                .build()
        }
    }
}
