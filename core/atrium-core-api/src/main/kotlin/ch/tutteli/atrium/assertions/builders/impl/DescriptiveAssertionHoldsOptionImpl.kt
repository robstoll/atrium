package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionBuilder
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionHoldsOption

internal object DescriptiveAssertionHoldsOptionImpl : DescriptiveAssertionHoldsOption {
    private val falseProvider = { false }
    private val trueProvider = { true }

    override val failing: DescriptiveAssertionBuilder = DescriptiveAssertionBuilderImpl(falseProvider)
    override val holding: DescriptiveAssertionBuilder = DescriptiveAssertionBuilderImpl(trueProvider)

    override fun withTest(test: () -> Boolean): DescriptiveAssertionBuilder = DescriptiveAssertionBuilderImpl(test)
}
