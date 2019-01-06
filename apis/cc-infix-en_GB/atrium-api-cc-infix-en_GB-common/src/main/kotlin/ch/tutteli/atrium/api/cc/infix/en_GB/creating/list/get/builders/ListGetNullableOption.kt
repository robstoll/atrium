package ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.impl.ListGetNullableOptionImpl
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * Represents the extension point for another option after a `get index`-step within a
 * sophisticated `get` assertion building process for [List].
 *
 * @param E The entry type of the [List].
 * @param T A subtype of [List].
 */
interface ListGetNullableOption<E, T: List<E>> {
    /**
     * The [AssertionPlant] for which this assertion is created
     */
    val plant: Assert<T>

    /**
     * The given index which will be used to perform the [List.get].
     */
    val index: Int

    /**
     * Makes the assertion that the given [index] is within the bounds of [AssertionPlant.subject] and that
     * the corresponding nullable entry holds all assertions the given [assertionCreator] might create for it.
     *
     * Notice, that the corresponding entry of the given [index] can be `null` even if the index is within bounds
     * as the [List] has a nullable entry type.
     *
     * @return This plant to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
     *   does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertIt(assertionCreator: AssertionPlantNullable<E>.() -> Unit): Assert<T>

    companion object {
        fun <E, T: List<E>> create(plant: Assert<T>, index: Int): ListGetNullableOption<E, T>
            = ListGetNullableOptionImpl(plant, index)
    }
}

