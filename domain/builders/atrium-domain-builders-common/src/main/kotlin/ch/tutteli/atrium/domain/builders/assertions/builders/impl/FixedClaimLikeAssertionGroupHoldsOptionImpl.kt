package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionGroupDescriptionAndRepresentationOption
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimLikeAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal abstract class FixedClaimLikeAssertionGroupHoldsOptionImpl<T : AssertionGroupType, R>(
    override val groupType: T
) : FixedClaimLikeAssertionGroupHoldsOption<T, R> {
    override val holding: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>
        get() = createDescriptionAndRepresentationOption(true)

    override val failing: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>
        get() = createDescriptionAndRepresentationOption(false)

    override fun withClaim(holds: Boolean): AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>
        = createDescriptionAndRepresentationOption(holds)

    private fun createDescriptionAndRepresentationOption(holds: Boolean)
        = AssertionGroupDescriptionAndRepresentationOption.create(groupType, createAssertionOptionWithHolds(holds))

    private fun createAssertionOptionWithHolds(holds: Boolean): (T, Translatable, Any) -> AssertionsOption<T, R>
        = { t, d, r -> AssertionsOption.create(t, d, r, createFixedClaimLikeAssertionGroupFinalStep(holds)) }

    protected abstract fun createFixedClaimLikeAssertionGroupFinalStep(
        holds: Boolean
    ): (T, Translatable, Any, List<Assertion>) -> R
}
