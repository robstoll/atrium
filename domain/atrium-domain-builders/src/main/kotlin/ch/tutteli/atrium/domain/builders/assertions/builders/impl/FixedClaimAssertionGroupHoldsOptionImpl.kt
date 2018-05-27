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
        get() = createDescriptionAndRepresentationOption(true)

    override val failing: DescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>
        get() = createDescriptionAndRepresentationOption(false)

    override fun withClaim(holds: Boolean): DescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>
        = createDescriptionAndRepresentationOption(holds)

    private fun createDescriptionAndRepresentationOption(holds: Boolean)
        = DescriptionAndRepresentationOption.create(groupType, createAssertionOptionWithHolds(holds))

    private fun createAssertionOptionWithHolds(holds: Boolean): (T, Translatable, Any) -> AssertionsOption<T, FixedClaimAssertionGroupFinalStep>
        = { t, d, r -> AssertionsOption.create(t, d, r, createFixedClaimAssertionGroupFinalStep(holds)) }

    private fun createFixedClaimAssertionGroupFinalStep(holds: Boolean): (T, Translatable, Any, List<Assertion>) -> FixedClaimAssertionGroupFinalStep
        = {t, d, r, a -> FixedClaimAssertionGroupFinalStep.create(t, d, r, a, holds)}
}
