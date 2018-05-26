package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixHoldsAssertionGroupBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.FixHoldsAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixHoldsAssertionGroupHoldsOptionImpl<out T: AssertionGroupType>(
    override val name: Translatable,
    override val subject: Any,
    override val groupType: T
) : FixHoldsAssertionGroupHoldsOption<T> {

    override val holding: FixHoldsAssertionGroupBuilder<T>
        get() = FixHoldsAssertionGroupBuilderImpl(name, subject, groupType, true)

    override val failing: FixHoldsAssertionGroupBuilder<T>
        get() = FixHoldsAssertionGroupBuilderImpl(name, subject, groupType, false)

    override fun withClaim(holds: Boolean): FixHoldsAssertionGroupBuilder<T>
        = FixHoldsAssertionGroupBuilderImpl(name, subject, groupType, holds)
}
