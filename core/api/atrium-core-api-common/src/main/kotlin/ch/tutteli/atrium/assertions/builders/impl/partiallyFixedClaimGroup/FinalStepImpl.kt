package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.PartiallyFixedClaimGroup
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    override val preTransformationHolds: Boolean
) : PartiallyFixedClaimGroup.FinalStep {

    override fun build(): AssertionGroup
        = PartiallyFixedClaimAssertionGroup(groupType, description, representation, assertions, preTransformationHolds)
}
