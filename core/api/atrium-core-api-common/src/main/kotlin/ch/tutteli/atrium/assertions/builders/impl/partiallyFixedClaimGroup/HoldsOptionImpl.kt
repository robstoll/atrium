package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.PartiallyFixedClaimGroup
import ch.tutteli.atrium.assertions.builders.impl.FixedClaimLikeAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

internal class HoldsOptionImpl<T : AssertionGroupType>(
    groupType: T
) : FixedClaimLikeAssertionGroupHoldsOptionImpl<T, PartiallyFixedClaimGroup.FinalStep>(groupType),
    PartiallyFixedClaimGroup.HoldsOption<T> {

    override fun createFixedClaimLikeAssertionGroupFinalStep(
        holds: Boolean
    ): (T, Translatable, Any, List<Assertion>) -> PartiallyFixedClaimGroup.FinalStep = { t, d, r, a ->
        PartiallyFixedClaimGroup.FinalStep.create(t, d, r, a, holds)
    }
}
