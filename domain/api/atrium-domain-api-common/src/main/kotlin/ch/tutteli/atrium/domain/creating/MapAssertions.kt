package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KClass

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
    fun <K, V: Any> contains(plant: AssertionPlant<Map<out K, V>>, keyValuePairs: List<Pair<K, V>>): Assertion
    fun <K, V: Any> containsNullable(plant: AssertionPlant<Map<out K, V?>>, type: KClass<V>, keyValuePairs: List<Pair<K, V?>>): Assertion
    fun <K, V: Any> containsKeyWithValueAssertions(plant: AssertionPlant<Map<out K, V>>, keyValues: List<Pair<K, Assert<V>.() -> Unit>>): Assertion
    fun <K, V: Any> containsKeyWithNullableValueAssertions(plant: AssertionPlant<Map<out K, V?>>, type: KClass<V>, keyValues: List<Pair<K, (Assert<V>.() -> Unit)?>>): Assertion
    fun <K> containsKey(plant: AssertionPlant<Map<out K, *>>, key: K): Assertion
    fun <K> containsNotKey(plant: AssertionPlant<Map<out K, *>>, key: K): Assertion

    fun <K, V: Any> getExisting(plant: AssertionPlant<Map<out K, V>>, key: K): AssertionPlant<V>
    fun <K, V: Any> getExisting(plant: AssertionPlant<Map<out K, V>>, key: K, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
    fun <K, V> getExistingNullable(plant: AssertionPlant<Map<out K, V>>, key: K): AssertionPlantNullable<V>
    fun <K, V> getExistingNullable(plant: AssertionPlant<Map<out K, V>>, key: K, assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assertion

    fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int): Assertion
    fun isEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    fun isNotEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    fun <K> keys(plant: AssertionPlant<Map<out K, *>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
    fun <V> values(plant: AssertionPlant<Map<*, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
}
