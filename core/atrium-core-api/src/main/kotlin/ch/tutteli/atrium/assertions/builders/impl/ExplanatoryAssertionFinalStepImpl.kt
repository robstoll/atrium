package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.BasicExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionFinalStep

internal class ExplanatoryAssertionFinalStepImpl(override val explanation: Any?) : ExplanatoryAssertionFinalStep {

    override fun build(): ExplanatoryAssertion = BasicExplanatoryAssertion(explanation)
}
