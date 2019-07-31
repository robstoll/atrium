@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl



fun <K : Any, V : Any> _keyValue(
    plant: AssertionPlant<Map.Entry<K, V>>,
    key: K,
    value: V
): Assertion = AssertImpl.collector.collect(plant) {
    addAssertion(AssertImpl.map.entry.key(this) { toBe(key) })
    addAssertion(AssertImpl.map.entry.value(this) { toBe(value) })
}

fun <K : Any> _key(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion
    = AssertImpl.collector.collect(plant) { property(Map.Entry<K, *>::key, assertionCreator) }

fun <V : Any> _value(plant: AssertionPlant<Map.Entry<*, V>>, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
    = AssertImpl.collector.collect(plant) { property(Map.Entry<*, V>::value, assertionCreator) }

fun <K> _nullableKey(
    plant: AssertionPlant<Map.Entry<K, *>>,
    assertionCreator: AssertionPlantNullable<K>.() -> Unit
): Assertion
    = AssertImpl.collector.collect(plant) { property(Map.Entry<K, *>::key).assertionCreator() }

fun <V> _nullableValue(
    plant: AssertionPlant<Map.Entry<*, V>>,
    assertionCreator: AssertionPlantNullable<V>.() -> Unit
): Assertion
    = AssertImpl.collector.collect(plant) { property(Map.Entry<*, V>::value).assertionCreator() }
