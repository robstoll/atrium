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
    fun <K, V: Any> getExisting(plant: AssertionPlant<Map<K, V>>, key: K, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
    fun <K, V> getExistingNullable(plant: AssertionPlant<Map<K, V>>, key: K, assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assertion
    fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int): Assertion
    fun isEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    fun isNotEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    fun <K> keys(plant: AssertionPlant<Map<K, *>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
    fun <V> values(plant: AssertionPlant<Map<*, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
}
