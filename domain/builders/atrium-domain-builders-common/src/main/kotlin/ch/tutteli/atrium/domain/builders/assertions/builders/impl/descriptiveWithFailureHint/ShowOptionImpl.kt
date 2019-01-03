package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint

internal class ShowOptionImpl(
    private val test: () -> Boolean,
    private val failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHint.ShowOption {

   override val showForAnyFailure get(): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep>
        = createDescriptiveLikeAssertionDescriptionOption(trueProvider)

    override fun showOnlyIf(
        predicate: () -> Boolean
    ): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep>
        = createDescriptiveLikeAssertionDescriptionOption(predicate)

    private fun createDescriptiveLikeAssertionDescriptionOption(
        predicate: () -> Boolean
    ): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(
            test,
            { t, d, r -> DescriptiveAssertionWithFailureHint.FinalStep.create(t, predicate, failureHintFactory, d, r) }
        )
}
