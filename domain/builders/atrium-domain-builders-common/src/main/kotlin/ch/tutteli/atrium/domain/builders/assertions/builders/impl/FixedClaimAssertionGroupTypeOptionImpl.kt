package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupTypeOption

internal object FixedClaimAssertionGroupTypeOptionImpl : FixedClaimAssertionGroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): FixedClaimAssertionGroupHoldsOption<T>
        = FixedClaimAssertionGroupHoldsOption.create(groupType)
}
