package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupFinalStep

internal class ExplanatoryAssertionGroupFinalStepImpl(
    override val groupType: ExplanatoryAssertionGroupType,
    override val explanatoryAssertions: List<Assertion>
) : ExplanatoryAssertionGroupFinalStep{

    override fun build(): AssertionGroup = ExplanatoryAssertionGroup(groupType, explanatoryAssertions)
}
