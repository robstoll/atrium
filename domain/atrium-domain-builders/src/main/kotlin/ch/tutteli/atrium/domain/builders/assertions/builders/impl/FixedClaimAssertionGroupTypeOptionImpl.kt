package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupTypeOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupTypeOptionImpl(
    override val name: Translatable,
    override val subject: Any
) : FixedClaimAssertionGroupTypeOption {

    override val withListType: FixedClaimAssertionGroupHoldsOption<ListAssertionGroupType>
        get() = FixedClaimAssertionGroupHoldsOptionImpl(name, subject, DefaultListAssertionGroupType)

    override fun <T : AssertionGroupType> withType(type: T): FixedClaimAssertionGroupHoldsOption<T>
        = FixedClaimAssertionGroupHoldsOptionImpl(name, subject, type)
}
