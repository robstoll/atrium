@file:Suppress(
/* TODO remove annotation with 1.0.0 */ "DEPRECATION",
/* TODO remove annotation with 1.0.0 */ "TYPEALIAS_EXPANSION_DEPRECATION"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*

abstract class MapAssertionsDeprecatedImpl : MapAssertions {

    override fun <K, V> contains(plant: AssertionPlant<Map<out K, V>>, keyValuePairs: List<Pair<K, V>>) =
        _contains(plant, keyValuePairs)

    override fun <K, V : Any> containsKeyWithValueAssertions(
        plant: AssertionPlant<Map<out K, V?>>,
        keyValues: List<Pair<K, (Assert<V>.() -> Unit)?>>
    ) = _containsKeyWithValueAssertion(plant, keyValues)

    override fun <K, V : Any> getExisting(plant: AssertionPlant<Map<out K, V>>, key: K) = _getExisting(plant, key)

    override fun <K, V : Any> getExisting(
        plant: AssertionPlant<Map<out K, V>>,
        key: K,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ) = _getExisting(plant, key, assertionCreator)

    override fun <K, V> getExistingNullable(plant: AssertionPlant<Map<out K, V>>, key: K) =
        _getExistingNullable(plant, key)

    override fun <K, V> getExistingNullable(
        plant: AssertionPlant<Map<out K, V>>,
        key: K,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ) = _getExistingNullable(plant, key, assertionCreator)


    override fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int) = _hasSize(plant, size)

    override fun <K> keys(
        plant: AssertionPlant<Map<out K, *>>,
        assertionCreator: AssertionPlant<Set<K>>.() -> Unit
    ): Assertion = _keys(plant, assertionCreator)

    override fun <V> values(
        plant: AssertionPlant<Map<*, V>>,
        assertionCreator: AssertionPlant<Collection<V>>.() -> Unit
    ): Assertion = _values(plant, assertionCreator)
}
