package ch.tutteli.atrium.domain.builders.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimGroup

internal object GroupTypeOptionImpl : PartiallyFixedClaimGroup.GroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): PartiallyFixedClaimGroup.HoldsOption<T>
        = PartiallyFixedClaimGroup.HoldsOption.create(groupType)
}
