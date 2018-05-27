package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionHoldsOption
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption

internal object DescriptiveAssertionHoldsOptionImpl : DescriptiveAssertionHoldsOption {
    private val falseProvider = { false }
    private val trueProvider = { true }

    override val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOptionImpl(falseProvider, ::DescriptiveAssertionFinalStepImpl)

    override val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOptionImpl(trueProvider, ::DescriptiveAssertionFinalStepImpl)

    override fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
        = DescriptiveLikeAssertionDescriptionOptionImpl(test, ::DescriptiveAssertionFinalStepImpl)
}
