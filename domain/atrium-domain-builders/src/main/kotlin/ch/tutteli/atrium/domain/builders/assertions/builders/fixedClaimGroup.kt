package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.AssertionBuilderFinalStep
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.AssertionGroupDescriptionAndRepresentationOption
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupFinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable


/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions]).
 */
@Suppress("unused")
val AssertionBuilder.fixedClaimGroup: FixedClaimAssertionGroupTypeOption
    get() = FixedClaimAssertionGroupTypeOptionImpl


/**
 * Provides options to specify the [AssertionGroup.type].
 */
interface FixedClaimAssertionGroupTypeOption {
    /**
     * Defines that a [ListAssertionGroupType] shall be used for [AssertionGroup.type].
     */
    val withListType: FixedClaimAssertionGroupHoldsOption<ListAssertionGroupType>

    /**
     * Uses the given [type] as [AssertionGroup.type].
     *
     * @param type The [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> withType(type: T): FixedClaimAssertionGroupHoldsOption<T>
}


/**
 * Provides options to specify the [AssertionGroup.holds].
 */
interface FixedClaimAssertionGroupHoldsOption<T : AssertionGroupType> {
    /**
     * Defines the [AssertionGroup.type].
     */
    val groupType: T

    /**
     * Defines the [AssertionGroup] holds.
     */
    val holding: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>

    /**
     * Defines the [AssertionGroup] does not hold.
     */
    val failing: AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>

    /**
     * Uses the given [holds] as [AssertionGroup.holds].
     */
    fun withClaim(holds: Boolean): AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, FixedClaimAssertionGroupFinalStep>>

    companion object {
        fun <T: AssertionGroupType> create(groupType: T): FixedClaimAssertionGroupHoldsOption<T>
            = FixedClaimAssertionGroupHoldsOptionImpl(groupType)
    }
}

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but on the given [holds]).
 */
interface FixedClaimAssertionGroupFinalStep : AssertionBuilderFinalStep<AssertionGroup> {
    /**
     * Defines the [AssertionGroup.holds].
     */
    val holds: Boolean

    companion object {
        fun create(
            groupType: AssertionGroupType,
            description: Translatable,
            representation: Any,
            assertions: List<Assertion>,
            holds: Boolean
        ): FixedClaimAssertionGroupFinalStep
            = FixedClaimAssertionGroupFinalStepImpl(groupType, description, representation, assertions, holds)
    }
}
