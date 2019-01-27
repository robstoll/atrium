package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl.MapGetNullableOptionImpl
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * Represents the extension point for another option after a `get key`-step within a
 * sophisticated `get` assertion building process for [Map].
 *
 * @param K The key type of the [Map].
 * @param V the value type of the [Map].
 * @param T A subtype of [Map].
 */
interface MapGetNullableOption<K, V, T: Map<K, V>> {
    /**
     * The [AssertionPlant] for which this assertion is created
     */
    val plant: Assert<T>

    /**
     * The given key which will be used to perform the [Map.get].
     */
    val key: K

    /**
     * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains the previously specified [key] and that the
     * corresponding nullable value holds all assertions the given [assertionCreator] might create for it.
     *
     * Notice, that the corresponding value of the given [key] can be `null` even if the key exists as the [Map] has a
     * nullable value type.
     *
     * @return This plant to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
     *   does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertValue(assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assert<T>

    companion object {
        fun <K, V, T: Map<K, V>> create(plant: Assert<T>, key: K): MapGetNullableOption<K, V, T>
            = MapGetNullableOptionImpl(plant, key)
    }
}

