package ch.tutteli.atrium.assertions.builders.impl.fixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.FixedClaimGroup
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    override val holds: Boolean
) : FixedClaimGroup.FinalStep {

    override fun build(): AssertionGroup
        = FixedClaimAssertionGroup(groupType, description, representation, assertions, holds)
}
