package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupHoldsOptionImpl<T : AssertionGroupType>(
    override val groupType: T
) : FixedClaimLikeAssertionGroupHoldsOptionImpl<T, FixedClaimAssertionGroupFinalStep>(groupType),
    FixedClaimAssertionGroupHoldsOption<T> {

    override fun createFixedClaimLikeAssertionGroupFinalStep(
        holds: Boolean
    ): (T, Translatable, Any, List<Assertion>) -> FixedClaimAssertionGroupFinalStep = { t, d, r, a ->
        FixedClaimAssertionGroupFinalStep.create(t, d, r, a, holds)
    }
}
