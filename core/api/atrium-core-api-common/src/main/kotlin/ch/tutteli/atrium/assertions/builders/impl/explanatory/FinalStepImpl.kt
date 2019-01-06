@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.builders.impl.explanatory

import ch.tutteli.atrium.assertions.BasicExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.Explanatory

internal class FinalStepImpl(override val explanation: Any?) : Explanatory.FinalStep {

    override fun build(): ExplanatoryAssertion = BasicExplanatoryAssertion(explanation)
}
