package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupHoldsOptionImpl<out T: AssertionGroupType>(
    override val name: Translatable,
    override val subject: Any,
    override val groupType: T
) : FixedClaimAssertionGroupHoldsOption<T> {

    override val holding: FixedClaimAssertionGroupBuilder<T>
        get() = FixedClaimAssertionGroupBuilderImpl(name, subject, groupType, true)

    override val failing: FixedClaimAssertionGroupBuilder<T>
        get() = FixedClaimAssertionGroupBuilderImpl(name, subject, groupType, false)

    override fun withClaim(holds: Boolean): FixedClaimAssertionGroupBuilder<T>
        = FixedClaimAssertionGroupBuilderImpl(name, subject, groupType, holds)
}
