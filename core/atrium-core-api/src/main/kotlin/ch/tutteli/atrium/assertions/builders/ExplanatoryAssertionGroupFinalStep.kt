package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.ExplanatoryAssertionGroupFinalStepImpl

interface ExplanatoryAssertionGroupFinalStep: AssertionBuilderFinalStep<AssertionGroup>{
    val groupType: ExplanatoryAssertionGroupType
    val explanatoryAssertions: List<Assertion>

    companion object {
        fun create(
            groupType: ExplanatoryAssertionGroupType,
            explanatoryAssertions: List<Assertion>
        ): ExplanatoryAssertionGroupFinalStep = ExplanatoryAssertionGroupFinalStepImpl(groupType, explanatoryAssertions)
    }
}
