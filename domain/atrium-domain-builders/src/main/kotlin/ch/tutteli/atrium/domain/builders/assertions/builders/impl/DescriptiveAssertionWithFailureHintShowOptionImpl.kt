package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintFinalStep
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintShowOption

internal class DescriptiveAssertionWithFailureHintShowOptionImpl(
    private val test: () -> Boolean,
    private val failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption {

   override val showForAnyFailure get(): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>
        = descriptiveLikeAssertionDescriptionOption({ true })

    override fun showOnlyIf(predicate: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>
        = descriptiveLikeAssertionDescriptionOption(predicate)

    private fun descriptiveLikeAssertionDescriptionOption(predicate: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(test, { t, d, r -> DescriptiveAssertionWithFailureHintFinalStepImpl(t, predicate, failureHintFactory, d, r) })
}
