package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.PartiallyFixedClaimGroup

internal object GroupTypeOptionImpl : PartiallyFixedClaimGroup.GroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): PartiallyFixedClaimGroup.HoldsOption<T>
        = PartiallyFixedClaimGroup.HoldsOption.create(groupType)
}
