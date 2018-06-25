package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupFinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    override val holds: Boolean
) : FixedClaimAssertionGroupFinalStep {

    override fun build(): AssertionGroup
        = FixedClaimAssertionGroup(groupType, description, representation, assertions, holds)
}
