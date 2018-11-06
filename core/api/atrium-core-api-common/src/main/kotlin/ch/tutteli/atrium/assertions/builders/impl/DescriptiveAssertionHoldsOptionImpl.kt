package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionHoldsOption
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.trueProvider

internal object DescriptiveAssertionHoldsOptionImpl : DescriptiveAssertionHoldsOption {

    override val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        //TODO use falseProvider https://youtrack.jetbrains.com/issue/KT-27736
        = DescriptiveLikeAssertionDescriptionOption.create({ false }, DescriptiveAssertionFinalStep.Companion::create)

    override val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        //TODO use trueProvider https://youtrack.jetbrains.com/issue/KT-27736
        = DescriptiveLikeAssertionDescriptionOption.create({ true }, DescriptiveAssertionFinalStep.Companion::create)

    override fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOption.create(test, DescriptiveAssertionFinalStep.Companion::create)
}
