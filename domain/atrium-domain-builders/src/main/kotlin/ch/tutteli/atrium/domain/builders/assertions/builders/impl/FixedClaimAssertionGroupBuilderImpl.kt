package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupBuilderImpl<out T : AssertionGroupType>(
    override val name: Translatable,
    override val subject: Any,
    override val groupType: T,
    override val holds: Boolean
) : FixedClaimAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = FixedClaimAssertionGroup(groupType, name, subject, assertions, holds)
}
