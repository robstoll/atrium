package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupFinalStep

internal class ExplanatoryAssertionGroupFinalStepImpl(
    override val groupType: ExplanatoryAssertionGroupType,
    override val explanatoryAssertions: List<Assertion>
) : ExplanatoryAssertionGroupFinalStep{

    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
    override fun build(): AssertionGroup = ExplanatoryAssertionGroup(groupType, explanatoryAssertions)
}
