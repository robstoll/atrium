package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.BasicAssertionGroupFinalStepImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Final step which creates an [AssertionGroup] with the previously defined [groupType], [description],
 * [representation] and [assertions].
 */
interface BasicAssertionGroupFinalStep : AssertionBuilderFinalStep<AssertionGroup> {

    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: AssertionGroupType

    /**
     * The previously defined [AssertionGroup.name].
     */
    val description: Translatable

    /**
     * The previously defined [AssertionGroup.representation].
     */
    val representation: Any

    /**
     * The previously defined [AssertionGroup.assertions]
     */
    val assertions: List<Assertion>

    companion object {
        fun create(
            groupType: AssertionGroupType,
            name: Translatable,
            representation: Any,
            assertions: List<Assertion>
        ): BasicAssertionGroupFinalStep = BasicAssertionGroupFinalStepImpl(groupType, name, representation, assertions)
    }
}
