package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BasicAssertionGroup
import ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal class BasicAssertionGroupBuilderImpl<out T : AssertionGroupType>(
    override val groupType: T,
    override val name: Translatable,
    override val representation: Any
) : BasicAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = BasicAssertionGroup(groupType, name, representation, assertions)
}
