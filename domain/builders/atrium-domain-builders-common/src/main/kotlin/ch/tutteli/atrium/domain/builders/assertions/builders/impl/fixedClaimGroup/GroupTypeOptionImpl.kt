package ch.tutteli.atrium.domain.builders.assertions.builders.impl.fixedClaimGroup

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimGroup

internal object GroupTypeOptionImpl : FixedClaimGroup.GroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): FixedClaimGroup.HoldsOption<T>
        = FixedClaimGroup.HoldsOption.create(groupType)
}
