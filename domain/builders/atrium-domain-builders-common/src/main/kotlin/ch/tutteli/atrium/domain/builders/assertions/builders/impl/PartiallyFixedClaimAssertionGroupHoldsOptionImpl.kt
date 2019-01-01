package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class PartiallyFixedClaimAssertionGroupHoldsOptionImpl<T : AssertionGroupType>(
    groupType: T
) : FixedClaimLikeAssertionGroupHoldsOptionImpl<T, PartiallyFixedClaimAssertionGroupFinalStep>(groupType),
    PartiallyFixedClaimAssertionGroupHoldsOption<T> {

    override fun createFixedClaimLikeAssertionGroupFinalStep(
        holds: Boolean
    ): (T, Translatable, Any, List<Assertion>) -> PartiallyFixedClaimAssertionGroupFinalStep = { t, d, r, a ->
        PartiallyFixedClaimAssertionGroupFinalStep.create(t, d, r, a, holds)
    }
}
