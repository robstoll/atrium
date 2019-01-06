@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.builders.impl.descriptive

import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val description: Translatable,
    override val representation: Any
) : Descriptive.FinalStep {

    override fun build(): DescriptiveAssertion = BasicDescriptiveAssertion(description, representation, test)
}
