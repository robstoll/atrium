package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.AssertionGroupBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixHoldsAssertionGroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but specified by the [FixHoldsAssertionGroupHoldsOption]).
 *
 * @param name The [AssertionGroup.name].
 * @param representation The [AssertionGroup.representation].
 */
@Suppress("unused")
fun AssertionBuilder.fixHoldsGroup(name: Translatable, representation: Any): FixHoldsAssertionGroupTypeOption
    = FixHoldsAssertionGroupTypeOptionImpl(name, representation)

/**
 * Provides options to specify the [AssertionGroup.type].
 */
interface FixHoldsAssertionGroupTypeOption {
    val name: Translatable
    val subject: Any

    /**
     * Defines that a [ListAssertionGroupType] shall be used for [AssertionGroup.type].
     */
    val withListType: FixHoldsAssertionGroupHoldsOption<ListAssertionGroupType>

    /**
     * Uses the given [type] as [AssertionGroup.type].
     *
     * @param type The [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> withType(type: T): FixHoldsAssertionGroupHoldsOption<T>
}

/**
 * Provides options to specify the [AssertionGroup.holds].
 */
interface FixHoldsAssertionGroupHoldsOption<out T: AssertionGroupType> {
    val name: Translatable
    val subject: Any
    val groupType: T

    /**
     * Defines that the [AssertionGroup] holds.
     */
    val holding: FixHoldsAssertionGroupBuilder<T>

    /**
     * Defines that the [AssertionGroup] does not hold.
     */
    val failing: FixHoldsAssertionGroupBuilder<T>

    /**
     * Uses the given [holds] as [AssertionGroup.holds].
     */
    fun withClaim(holds: Boolean): FixHoldsAssertionGroupBuilder<T>
}


/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but specified by property [holds]).
 */
interface FixHoldsAssertionGroupBuilder<out T : AssertionGroupType> : AssertionGroupBuilder<T> {
    val name: Translatable
    val subject: Any
    val holds: Boolean
}

