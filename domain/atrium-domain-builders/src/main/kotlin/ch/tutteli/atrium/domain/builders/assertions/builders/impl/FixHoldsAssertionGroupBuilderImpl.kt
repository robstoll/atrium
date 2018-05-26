package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixHoldsAssertionGroupBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixHoldsAssertionGroupBuilderImpl<out T : AssertionGroupType>(
    override val name: Translatable,
    override val subject: Any,
    override val groupType: T,
    override val holds: Boolean
) : FixHoldsAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = FixHoldsAssertionGroup(groupType, name, subject, assertions, holds)
}
