package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.PartiallyFixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class PartiallyFixedClaimAssertionGroupFinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    override val preTransformationHolds: Boolean
) : PartiallyFixedClaimAssertionGroupFinalStep {

    override fun build(): AssertionGroup
        = PartiallyFixedClaimAssertionGroup(groupType, description, representation, assertions, preTransformationHolds)
}
