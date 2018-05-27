package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionHoldsOption
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.trueProvider

internal object DescriptiveAssertionHoldsOptionImpl : DescriptiveAssertionHoldsOption {

    override val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(falseProvider, DescriptiveAssertionFinalStep.Companion::create)

    override val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(trueProvider, DescriptiveAssertionFinalStep.Companion::create)

    override fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(test, DescriptiveAssertionFinalStep.Companion::create)
}
