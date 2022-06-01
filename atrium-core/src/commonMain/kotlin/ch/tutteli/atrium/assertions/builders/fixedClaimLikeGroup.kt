package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*

/**
 * Defines the contract to build an [AssertionGroup] which has a fixed [AssertionGroup.holds] or the like.
 *
 * With the like is meant that [AssertionGroup.holds] does either not at all or at least not only depend on
 * [AssertionGroup.assertions] but also on a fixed part.
 */
interface FixedClaimLikeGroup {

    /**
     * Option step which allows to specify the [AssertionGroup.type].
     */
    interface GroupTypeOption<R> {

        /**
         * Uses [ListAssertionGroupType] as [AssertionGroup.type].
         */
        val withFeatureType: HoldsOption<FeatureAssertionGroupType, R>
            get() = withType(DefaultFeatureAssertionGroupType)

        /**
         * Uses [ListAssertionGroupType] as [AssertionGroup.type].
         */
        val withListType: HoldsOption<ListAssertionGroupType, R>
            get() = withType(DefaultListAssertionGroupType)

        /**
         * Uses the given [groupType] as [AssertionGroup.type].
         *
         * @param groupType The [AssertionGroup.type].
         */
        fun <T : AssertionGroupType> withType(groupType: T): HoldsOption<T, R>
    }

    /**
     * Option step which allows to specify the [AssertionGroup.holds] or another fixed part involved
     * in calculating [AssertionGroup.holds].
     */
    interface HoldsOption<T : AssertionGroupType, R> {

        /**
         * The previously defined [AssertionGroup.type].
         */
        val groupType: T

        /**
         * Defines the [AssertionGroup] (or the fixed part) holds
         */
        val holding: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>

        /**
         * Defines the [AssertionGroup] (or the fixed part) does not hold.
         */
        val failing: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>

        /**
         * Uses the given [holds] as [AssertionGroup.holds] (or for the fixed part).
         */
        fun withClaim(holds: Boolean): AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, R>>
    }
}
