package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimAssertionGroupTypeOption

internal object PartiallyFixedClaimAssertionGroupTypeOptionImpl : PartiallyFixedClaimAssertionGroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): PartiallyFixedClaimAssertionGroupHoldsOption<T>
        = PartiallyFixedClaimAssertionGroupHoldsOption.create(groupType)
}
