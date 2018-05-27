package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionHoldsOption
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption

internal object DescriptiveAssertionHoldsOptionImpl : DescriptiveAssertionHoldsOption {
    private val falseProvider = { false }
    private val trueProvider = { true }

    override val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(falseProvider, DescriptiveAssertionFinalStep.Companion::create)

    override val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(trueProvider, DescriptiveAssertionFinalStep.Companion::create)

    override fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(test, DescriptiveAssertionFinalStep.Companion::create)
}
