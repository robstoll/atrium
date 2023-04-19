package ch.tutteli.atrium.assertions.builders.impl.explanatory

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.Explanatory

internal object ExplanationOptionImpl : Explanatory.ExplanationOption {

    override fun withExplanation(explanation: Any?): Explanatory.FinalStep = Explanatory.FinalStep.create(explanation)
}

internal class FinalStepImpl(override val explanation: Any?) : Explanatory.FinalStep {

    override fun build(): ExplanatoryAssertion = ch.tutteli.atrium.assertions.BasicExplanatoryAssertion(explanation)
}
