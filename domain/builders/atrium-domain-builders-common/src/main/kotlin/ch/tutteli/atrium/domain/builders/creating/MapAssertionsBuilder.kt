@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.domain.creating.mapAssertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [MapAssertions].
 * In detail, it implements [MapAssertions] by delegating to [mapAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object MapAssertionsBuilder : MapAssertions {
    override inline fun <K, V: Any> contains(plant: AssertionPlant<Map<K, V>>, keyValuePairs: List<Pair<K, V>>): Assertion
        = mapAssertions.contains(plant, keyValuePairs)

    override inline fun <K, V: Any> containsNullable(plant: AssertionPlant<Map<K, V?>>, type: KClass<V>, keyValuePairs: List<Pair<K, V?>>): Assertion
        = mapAssertions.containsNullable(plant, type, keyValuePairs)

    override inline fun <K, V : Any> containsKeyWithValueAssertions(
        plant: AssertionPlant<Map<K, V>>,
        keyValues: List<KeyValue<K, V>>
    ) = mapAssertions.containsKeyWithValueAssertions(plant, keyValues)

    override inline fun <K, V : Any> containsKeyWithNullableValueAssertions(
        plant: AssertionPlant<Map<K, V?>>,
        type: KClass<V>,
        keyValues: List<KeyNullableValue<K, V>>
    ) = mapAssertions.containsKeyWithNullableValueAssertions(plant, type, keyValues)

    override inline fun <K> containsKey(plant: AssertionPlant<Map<K, *>>, key: K)
        = mapAssertions.containsKey(plant, key)

    override inline fun <K> containsNotKey(plant: AssertionPlant<Map<K, *>>, key: K)
        = mapAssertions.containsNotKey(plant, key)

    override inline fun <K, V : Any> getExisting(
        plant: AssertionPlant<Map<K, V>>,
        key: K,
        noinline assertionCreator: AssertionPlant<V>.() -> Unit
    ) = mapAssertions.getExisting(plant, key, assertionCreator)

    override inline fun <K, V> getExistingNullable(
        plant: AssertionPlant<Map<K, V>>,
        key: K,
        noinline assertionCreator: AssertionPlantNullable<V>.() -> Unit
    )= mapAssertions.getExistingNullable(plant, key, assertionCreator)

    override inline fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int)
        = mapAssertions.hasSize(plant, size)

    override inline fun isEmpty(plant: AssertionPlant<Map<*, *>>)
        = mapAssertions.isEmpty(plant)

    override inline fun isNotEmpty(plant: AssertionPlant<Map<*, *>>)
        = mapAssertions.isNotEmpty(plant)

    override inline fun <K> keys(
        plant: AssertionPlant<Map<K, *>>,
        noinline assertionCreator: AssertionPlant<Set<K>>.() -> Unit
    ): Assertion = mapAssertions.keys(plant, assertionCreator)

    override inline fun <V> values(
        plant: AssertionPlant<Map<*, V>>,
        noinline assertionCreator: AssertionPlant<Collection<V>>.() -> Unit
    ): Assertion = mapAssertions.values(plant, assertionCreator)

}
