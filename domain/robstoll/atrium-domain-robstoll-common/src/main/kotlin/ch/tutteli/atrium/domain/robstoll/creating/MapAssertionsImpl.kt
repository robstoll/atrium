package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass

/**
 * Robstoll's implementation of [MapAssertions].
 */
class MapAssertionsImpl : MapAssertions {

    override fun <K, V: Any> contains(plant: AssertionPlant<Map<K, V>>, keyValuePairs: List<Pair<K, V>>)
        = _contains(plant, keyValuePairs)

    override fun <K, V: Any> containsNullable(
        plant: AssertionPlant<Map<K, V?>>,
        type: KClass<V>,
        keyValuePairs: List<Pair<K, V?>>
    ) = _containsNullable(plant, type, keyValuePairs)

    override fun <K, V : Any> containsKeyWithValueAssertions(
        plant: AssertionPlant<Map<K, V>>,
        keyValues: List<KeyValue<K, V>>
    ) = _containsKeyWithValueAssertion(plant, keyValues)

    override fun <K, V : Any> containsKeyWithNullableValueAssertions(
        plant: AssertionPlant<Map<K, V?>>,
        type: KClass<V>,
        keyValues: List<KeyNullableValue<K, V>>
    ) = _containsKeyWithNullableValueAssertions(plant, type, keyValues)

    override fun <K> containsKey(plant: AssertionPlant<Map<K, *>>, key: K)
        = _containsKey(plant, key)

    override fun <K> containsNotKey(plant: AssertionPlant<Map<K, *>>, key: K)
        = _containsNotKey(plant, key)

    override fun <K, V : Any> getExisting(
        plant: AssertionPlant<Map<K, V>>,
        key: K,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ) = _getExisting(plant, key, assertionCreator)

    override fun <K, V> getExistingNullable(
        plant: AssertionPlant<Map<K, V>>,
        key: K,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ) = _getExistingNullable(plant, key, assertionCreator)

    override fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int)
        = _hasSize(plant, size)

    override fun isEmpty(plant: AssertionPlant<Map<*, *>>)
        = _isEmpty(plant)

    override fun isNotEmpty(plant: AssertionPlant<Map<*, *>>)
        = _isNotEmpty(plant)

    override fun <K> keys(plant: AssertionPlant<Map<K, *>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
        = _keys(plant, assertionCreator)

    override fun <V> values(plant: AssertionPlant<Map<*, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
        = _values(plant, assertionCreator)

}
