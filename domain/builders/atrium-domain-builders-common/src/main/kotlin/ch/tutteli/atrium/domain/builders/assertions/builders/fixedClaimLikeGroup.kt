package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionGroupDescriptionAndRepresentationOption
import ch.tutteli.atrium.assertions.builders.AssertionsOption

/**
 * Option step which allows to specify the [AssertionGroup.type].
 */
interface FixedClaimLikeAssertionGroupTypeOption<R> {

    /**
     * Uses [ListAssertionGroupType] as [AssertionGroup.type].
     */
    val withFeatureType: FixedClaimLikeAssertionGroupHoldsOption<FeatureAssertionGroupType, R>
        get() = withType(DefaultFeatureAssertionGroupType)

    /**
     * Uses [ListAssertionGroupType] as [AssertionGroup.type].
     */
    val withListType: FixedClaimLikeAssertionGroupHoldsOption<ListAssertionGroupType, R>
        get() = withType(DefaultListAssertionGroupType)

    /**
     * Uses the given [groupType] as [AssertionGroup.type].
     *
     * @param groupType The [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> withType(groupType: T): FixedClaimLikeAssertionGroupHoldsOption<T, R>
}

/**
 * Option step which allows to specify the [AssertionGroup.holds] or another fixed part involved
 * in calculating [AssertionGroup.holds].
 */
interface FixedClaimLikeAssertionGroupHoldsOption<T : AssertionGroupType, R> {

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
