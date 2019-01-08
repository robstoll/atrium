package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapEntryAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._key
import ch.tutteli.atrium.domain.robstoll.lib.creating._nullableKey
import ch.tutteli.atrium.domain.robstoll.lib.creating._nullableValue
import ch.tutteli.atrium.domain.robstoll.lib.creating._value

/**
 * Robstoll's implementation of [MapEntryAssertions].
 */
class MapEntryAssertionsImpl : MapEntryAssertions {
    override fun <K : Any> key(
        plant: AssertionPlant<Map.Entry<K, *>>,
        assertionCreator: AssertionPlant<K>.() -> Unit
    ) = _key(plant, assertionCreator)

    override fun <V : Any> value(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ) = _value(plant, assertionCreator)

    override fun <K> nullableKey(
        plant: AssertionPlant<Map.Entry<K, *>>,
        assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ) = _nullableKey(plant, assertionCreator)

    override fun <V> nullableValue(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    )= _nullableValue(plant, assertionCreator)
}
