package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupBuilder

internal class ExplanatoryAssertionGroupBuilderImpl<out T: ExplanatoryAssertionGroupType>(
    override val groupType: T
) : ExplanatoryAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = ExplanatoryAssertionGroup(groupType, assertions)
}
