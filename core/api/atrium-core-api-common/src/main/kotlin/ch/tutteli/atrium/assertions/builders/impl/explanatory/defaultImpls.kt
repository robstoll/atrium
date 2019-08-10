
package ch.tutteli.atrium.assertions.builders.impl.explanatory

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.Explanatory

internal object ExplanationOptionImpl : Explanatory.ExplanationOption {

    override fun withDescription(explanation: Any?): Explanatory.FinalStep = Explanatory.FinalStep.create(explanation)
}


internal class FinalStepImpl(override val explanation: Any?) : Explanatory.FinalStep {

    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */ )
    override fun build(): ExplanatoryAssertion = BasicExplanatoryAssertion(explanation)
}
