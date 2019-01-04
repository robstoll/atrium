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
val mapAssertions by lazy { loadSingleService(MapAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Map],
 * which an implementation of the domain of Atrium has to provide.
 */
interface MapAssertions {
    fun <K> containsKey(plant: AssertionPlant<Map<K, *>>, key: K): Assertion
    fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>): Assertion
    fun <K, V> keys(plant: AssertionPlant<Map<K, V>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
    fun <K, V> values(plant: AssertionPlant<Map<K, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
    fun <K, V: Any> getExisting(plant: AssertionPlant<Map<K, V>>, key: K, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
    fun <K, V> getExistingNullable(plant: AssertionPlant<Map<K, V>>, key: K, assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assertion
}
