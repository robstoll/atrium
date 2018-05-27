package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionExplanationOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionFinalStep

internal object ExplanatoryAssertionExplanationOptionImpl : ExplanatoryAssertionExplanationOption {

    override fun withDescription(explanation: Any?): ExplanatoryAssertionFinalStep
        = ExplanatoryAssertionFinalStep.create(explanation)
}
