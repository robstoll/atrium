package ch.tutteli.atrium.assertions.builders.impl.fixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.FixedClaimGroup
import ch.tutteli.atrium.assertions.builders.impl.FixedClaimLikeAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

internal class HoldsOptionImpl<T : AssertionGroupType>(
    override val groupType: T
) : FixedClaimLikeAssertionGroupHoldsOptionImpl<T, FixedClaimGroup.FinalStep>(groupType),
    FixedClaimGroup.HoldsOption<T> {

    override fun createFixedClaimLikeAssertionGroupFinalStep(
        holds: Boolean
    ): (T, Translatable, Any, List<Assertion>) -> FixedClaimGroup.FinalStep = { t, d, r, a ->
        FixedClaimGroup.FinalStep.create(t, d, r, a, holds)
    }
}
