package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.impl.ExplanatoryAssertionFinalStepImpl

interface ExplanatoryAssertionFinalStep : AssertionBuilderFinalStep<ExplanatoryAssertion>{
    val explanation: Any?

    companion object {
        fun create(explanation: Any?): ExplanatoryAssertionFinalStep = ExplanatoryAssertionFinalStepImpl(explanation)
    }
}
