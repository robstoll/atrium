package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

fun <K : Any> _first(plant: AssertionPlant<Pair<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Pair<K, *>::first, assertionCreator) }

fun <V : Any> _second(plant: AssertionPlant<Pair<*, V>>, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Pair<*, V>::second, assertionCreator) }

fun <K> _nullableFirst(
    plant: AssertionPlant<Pair<K, *>>,
    assertionCreator: AssertionPlantNullable<K>.() -> Unit
): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Pair<K, *>::first).assertionCreator() }

fun <V> _nullableSecond(
    plant: AssertionPlant<Pair<*, V>>,
    assertionCreator: AssertionPlantNullable<V>.() -> Unit
): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Pair<*, V>::second).assertionCreator() }
