package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal object DescriptiveAssertionBuilderImpl : DescriptiveAssertionBuilder {

    override fun create(description: Translatable, representation: Any, test: () -> Boolean)
        = BasicDescriptiveAssertion(description, representation, test)
}
