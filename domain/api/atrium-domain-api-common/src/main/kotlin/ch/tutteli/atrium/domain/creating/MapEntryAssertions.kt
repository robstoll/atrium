package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * The access point to an implementation of [MapAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val mapEntryAssertions by lazy { loadSingleService(MapEntryAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Map],
 * which an implementation of the domain of Atrium has to provide.
 */
interface MapEntryAssertions {
    fun <K: Any, V: Any> isKeyValue(plant: AssertionPlant<Map.Entry<K, V>>, key: K, value: V): Assertion
    fun <K: Any> key(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion
    fun <V: Any> value(plant: AssertionPlant<Map.Entry<*, V>>, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
    fun <K> nullableKey(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlantNullable<K>.() -> Unit): Assertion
    fun <V> nullableValue(plant: AssertionPlant<Map.Entry<*, V>>, assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assertion
}
