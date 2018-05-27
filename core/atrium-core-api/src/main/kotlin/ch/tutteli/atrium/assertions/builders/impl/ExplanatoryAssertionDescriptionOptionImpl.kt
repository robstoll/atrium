package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionDescriptionOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionFinalStep

internal object ExplanatoryAssertionDescriptionOptionImpl : ExplanatoryAssertionDescriptionOption {

    override fun withDescription(explanation: Any?): ExplanatoryAssertionFinalStep
        = ExplanatoryAssertionFinalStep.create(explanation)
}
