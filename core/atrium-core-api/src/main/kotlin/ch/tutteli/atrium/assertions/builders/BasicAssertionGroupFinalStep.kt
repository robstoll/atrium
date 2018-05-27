package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.BasicAssertionGroupFinalStepImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Final step of the building process to create an [AssertionGroup] with the given [groupType], [description],
 * [representation] and [assertions].
 */
interface BasicAssertionGroupFinalStep : AssertionBuilderFinalStep<AssertionGroup> {

    /**
     * The [AssertionGroupType] which shall be used for [AssertionGroup.type].
     */
    val groupType: AssertionGroupType

    /**
     * The [AssertionGroup.name].
     */
    val description: Translatable

    /**
     * The [AssertionGroup.representation].
     */
    val representation: Any

    /**
     * The [AssertionGroup.assertions]
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
