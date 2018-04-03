package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.BasicExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionBuilder

internal object ExplanatoryAssertionBuilderImpl : ExplanatoryAssertionBuilder {

    override fun create(explanation: Any?) : ExplanatoryAssertion
        = BasicExplanatoryAssertion(explanation)
}
