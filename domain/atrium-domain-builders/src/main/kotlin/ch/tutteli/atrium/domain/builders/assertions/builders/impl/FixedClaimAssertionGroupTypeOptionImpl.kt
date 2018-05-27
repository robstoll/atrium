package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupTypeOption

internal object FixedClaimAssertionGroupTypeOptionImpl : FixedClaimAssertionGroupTypeOption {

    override val withListType: FixedClaimAssertionGroupHoldsOption<ListAssertionGroupType>
        get() = FixedClaimAssertionGroupHoldsOption.create(DefaultListAssertionGroupType)

    override fun <T : AssertionGroupType> withType(type: T): FixedClaimAssertionGroupHoldsOption<T>
        = FixedClaimAssertionGroupHoldsOption.create(type)
}
