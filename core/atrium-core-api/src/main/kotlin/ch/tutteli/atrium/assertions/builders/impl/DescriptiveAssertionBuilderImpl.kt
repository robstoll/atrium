package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptiveAssertionBuilderImpl(override val test: () -> Boolean) : DescriptiveAssertionBuilder {

    override fun create(description: Translatable, representation: Any)
        = BasicDescriptiveAssertion(description, representation, test)
}
