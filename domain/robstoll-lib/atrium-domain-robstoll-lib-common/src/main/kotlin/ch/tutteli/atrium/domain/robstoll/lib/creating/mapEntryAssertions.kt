package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

fun <K: Any> _key(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map.Entry<K, *>::key, assertionCreator) }

fun <V: Any> _value(plant: AssertionPlant<Map.Entry<*, V>>, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map.Entry<*, V>::value, assertionCreator) }

fun <K> _nullableKey(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlantNullable<K>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map.Entry<K, *>::key).assertionCreator() }

fun <V> _nullableValue(plant: AssertionPlant<Map.Entry<*, V>>, assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map.Entry<*, V>::value).assertionCreator() }
