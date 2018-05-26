package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.DescriptionAndRepresentationOption
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupHoldsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupHoldsOptionImpl<T: AssertionGroupType>(
    override val groupType: T
) : FixedClaimAssertionGroupHoldsOption<T> {

    override val holding: DescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>
        get() = descriptionAndRepresentationOption(true)

    override val failing: DescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>
        get() = descriptionAndRepresentationOption(false)

    override fun withClaim(holds: Boolean): DescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>
        = descriptionAndRepresentationOption(holds)


    private fun descriptionAndRepresentationOption(holds: Boolean)
        = DescriptionAndRepresentationOption.create(groupType, createAssertionOptionsWithHolds(holds))

    private fun createAssertionOptionsWithHolds(holds: Boolean): (T, Translatable, Any) -> AssertionsOption<T, FixedClaimAssertionGroupFinalStep>
        = { t, d, r -> AssertionsOption.create(t, d, r, createFixedClaimAssertionGroup(holds)) }

    private fun createFixedClaimAssertionGroup(holds: Boolean): (T, Translatable, Any, List<Assertion>) -> FixedClaimAssertionGroupFinalStep
        = {t, d, r, a -> FixedClaimAssertionGroupFinalStep.create(t, d, r, a, holds)}
}
