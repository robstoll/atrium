package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl.MapGetOptionImpl
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents the extension point for another option after a `get key`-step within a
 * sophisticated `get` assertion building process for [Map].
 *
 * @param K The key type of the map.
 * @param V the value type of the map.
 */
interface MapGetOption<K, V : Any> {
    /**
     * The [AssertionPlant] for which this assertion is created
     */
    val plant: Assert<Map<K, V>>

    /**
     * The given key which will be used to perform the [Map.get].
     */
    val key: K

    /**
     * Makes the assertion that [AssertionPlant.subject] contains the previously specified [key] and that the
     * corresponding value holds all assertions the given [assertionCreator] might create for it.
     *
     * @return This plant to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
     * does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertIt(assertionCreator: Assert<V>.() -> Unit): Assert<Map<K, V>>

    companion object {
        fun <K, V : Any> create(plant: Assert<Map<K, V>>, key: K): MapGetOption<K, V>
            = MapGetOptionImpl(plant, key)
    }
}

