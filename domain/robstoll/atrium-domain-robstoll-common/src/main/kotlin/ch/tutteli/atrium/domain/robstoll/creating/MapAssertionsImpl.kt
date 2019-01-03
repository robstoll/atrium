package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*

/**
 * Robstoll's implementation of [MapAssertions].
 */
class MapAssertionsImpl : MapAssertions {

    override fun <K, V> containsKey(plant: AssertionPlant<Map<K, V>>, key: K)
        = _containsKey(plant, key)

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
