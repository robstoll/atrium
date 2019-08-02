package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup.FinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup.GroupTypeOptionImpl
import ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup.HoldsOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is a logic AND operation composed by a fixed
 *  part and its [AssertionGroup.assertions].
 *
 * The intended use case is if the [AssertionGroup.assertions] depend on a pre-transformation (e.g., extract a feature,
 * type transformation etc.) which can either be successful or fail.
 * If it fails, then the assertions cannot be carried out (only be used for explanation) and in such a case
 * [AssertionGroup.holds] has to be false. Otherwise [AssertionGroup.holds] depend on [AssertionGroup.assertions].
 */
@Suppress("unused")
val AssertionBuilder.partiallyFixedClaimGroup: PartiallyFixedClaimGroup.GroupTypeOption
    get() = PartiallyFixedClaimGroup.GroupTypeOption.create()

/**
 * Defines the contract to build an [AssertionGroup] whose [AssertionGroup.holds] is a logic AND operation composed
 * by a fixed part and its [AssertionGroup.assertions].
 *
 * Have a look at [FixedClaimGroup] in case [AssertionGroup.holds] should not rely on [AssertionGroup.assertions] at
 * all but be fixed.
 */
interface PartiallyFixedClaimGroup {
    /**
     * Option step which allows to specify the [AssertionGroup.type].
     */
    interface GroupTypeOption : FixedClaimLikeGroup.GroupTypeOption<FinalStep> {
        companion object {
            fun create(): GroupTypeOption = GroupTypeOptionImpl
        }
    }

    /**
     * Option step which allows to specify the [AssertionGroup.holds] or another fixed part involved
     * in calculating [AssertionGroup.holds].
     */
    interface HoldsOption<T : AssertionGroupType> : FixedClaimLikeGroup.HoldsOption<T, FinalStep> {
        companion object {
            fun <T : AssertionGroupType> create(groupType: T): HoldsOption<T> = HoldsOptionImpl(groupType)
        }
    }

    /**
     * Final step which creates an [AssertionGroup] whose [AssertionGroup.holds] is a logic AND operation composed by
     * [preTransformationHolds] and its [AssertionGroup.assertions].
     */
    interface FinalStep : BasicAssertionGroupFinalStep {
        /**
         * The previously defined state of the pre-transformation (if it holds or not).
         */
        val preTransformationHolds: Boolean

        companion object {
            fun create(
                groupType: AssertionGroupType,
                description: Translatable,
                representation: Any,
                assertions: List<Assertion>,
                holds: Boolean
            ): FinalStep = FinalStepImpl(groupType, description, representation, assertions, holds)
        }
    }
}
