package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.fixedClaimGroup.FinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.fixedClaimGroup.HoldsOptionImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.fixedClaimGroup.GroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions]).
 *
 * The intended use case is if all [AssertionGroup.assertions] are [AssertionGroup]s with an
 * [ExplanatoryAssertionGroupType]. Such groups always return `true` for [AssertionGroup.holds] but you might want to
 * explain a complex failing assertion with those groups. In such a use case this builder is your choice.
 */
@Suppress("unused")
val AssertionBuilder.fixedClaimGroup: FixedClaimGroup.GroupTypeOption
    get() = FixedClaimGroup.GroupTypeOption.create()

/**
 * Defines the contract to build an [AssertionGroup] which has a fixed [AssertionGroup.holds].
 */
interface FixedClaimGroup {
    interface GroupTypeOption : FixedClaimLikeGroup.GroupTypeOption<FinalStep> {
        companion object {
            fun create(): GroupTypeOption = GroupTypeOptionImpl
        }
    }

    interface HoldsOption<T : AssertionGroupType> : FixedClaimLikeGroup.HoldsOption<T, FinalStep>{
        companion object {
            fun <T: AssertionGroupType> create(groupType: T): HoldsOption<T> = HoldsOptionImpl(groupType)
        }
    }

    /**
     * Final step which creates an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
     * [AssertionGroup.assertions] but on the given [holds]).
     */
    interface FinalStep: BasicAssertionGroupFinalStep {
        /**
         * The previously defined [AssertionGroup.holds].
         */
        val holds: Boolean

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
