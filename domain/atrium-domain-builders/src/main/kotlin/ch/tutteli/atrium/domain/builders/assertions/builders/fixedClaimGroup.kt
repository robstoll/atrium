package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.AssertionGroupBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupTypeOptionImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions]) using the given [name] and [RawString.EMPTY] as [AssertionGroup.representation].
 *
 * @param name The [AssertionGroup.name].
 */
@Suppress("unused")
fun AssertionBuilder.fixedClaimGroup(name: Translatable)
    = fixedClaimGroup(name, RawString.EMPTY)

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions]) using the given [name] and [representation].
 *
 * @param name The [AssertionGroup.name].
 * @param representation The [AssertionGroup.representation].
 */
@Suppress("unused")
fun AssertionBuilder.fixedClaimGroup(name: Translatable, representation: Any): FixedClaimAssertionGroupTypeOption
    = FixedClaimAssertionGroupTypeOptionImpl(name, representation)

/**
 * Provides options to specify the [AssertionGroup.type].
 */
interface FixedClaimAssertionGroupTypeOption {
    val name: Translatable
    val subject: Any

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
interface FixedClaimAssertionGroupHoldsOption<out T: AssertionGroupType> {
    val name: Translatable
    val subject: Any
    val groupType: T

    /**
     * Defines that the [AssertionGroup] holds.
     */
    val holding: FixedClaimAssertionGroupBuilder<T>

    /**
     * Defines that the [AssertionGroup] does not hold.
     */
    val failing: FixedClaimAssertionGroupBuilder<T>

    /**
     * Uses the given [holds] as [AssertionGroup.holds].
     */
    fun withClaim(holds: Boolean): FixedClaimAssertionGroupBuilder<T>
}


/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but specified by property [holds]).
 */
interface FixedClaimAssertionGroupBuilder<out T : AssertionGroupType> : AssertionGroupBuilder<T> {
    val name: Translatable
    val subject: Any
    val holds: Boolean
}

