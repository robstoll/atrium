@file:Suppress("DEPRECATION" /** TODO remove with 1.0.0 */)

package ch.tutteli.atrium.assertions.builders.impl.explanatoryGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.ExplanatoryGroup

internal class FinalStepImpl(
    override val groupType: ExplanatoryAssertionGroupType,
    override val explanatoryAssertions: List<Assertion>
) : ExplanatoryGroup.FinalStep{

    override fun build(): AssertionGroup = ExplanatoryAssertionGroup(groupType, explanatoryAssertions)
}
