package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptiveAssertionFinalStepImpl(
    override val test: () -> Boolean,
    override val description: Translatable,
    override val representation: Any
) : DescriptiveAssertionFinalStep {

    override fun build(): DescriptiveAssertion = BasicDescriptiveAssertion(description, representation, test)
}
