package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixHoldsAssertionGroupHoldsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixHoldsAssertionGroupTypeOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixHoldsAssertionGroupTypeOptionImpl(
    override val name: Translatable,
    override val subject: Any
) : FixHoldsAssertionGroupTypeOption {

    override val withListType: FixHoldsAssertionGroupHoldsOption<ListAssertionGroupType>
        get() = FixHoldsAssertionGroupHoldsOptionImpl(name, subject, DefaultListAssertionGroupType)

    override fun <T : AssertionGroupType> withType(type: T): FixHoldsAssertionGroupHoldsOption<T>
        = FixHoldsAssertionGroupHoldsOptionImpl(name, subject, type)
}
