package ch.tutteli.atrium.assertions.builders.impl.explanatory

import ch.tutteli.atrium.assertions.builders.Explanatory

internal object ExplanationOptionImpl : Explanatory.ExplanationOption {

    override fun withDescription(explanation: Any?): Explanatory.FinalStep = Explanatory.FinalStep.create(explanation)
}
