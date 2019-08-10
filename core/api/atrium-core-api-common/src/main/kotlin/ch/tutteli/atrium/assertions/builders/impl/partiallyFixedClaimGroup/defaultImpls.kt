package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.PartiallyFixedClaimGroup
import ch.tutteli.atrium.assertions.builders.impl.FixedClaimLikeAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

internal object GroupTypeOptionImpl : PartiallyFixedClaimGroup.GroupTypeOption {

    override fun <T : AssertionGroupType> withType(groupType: T): PartiallyFixedClaimGroup.HoldsOption<T> =
        PartiallyFixedClaimGroup.HoldsOption.create(groupType)
}

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

internal class FinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    override val preTransformationHolds: Boolean
) : PartiallyFixedClaimGroup.FinalStep {

    override fun build(): AssertionGroup =
        PartiallyFixedClaimAssertionGroup(groupType, description, representation, assertions, preTransformationHolds)
}
