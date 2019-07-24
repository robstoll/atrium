package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.ExpectImpl

fun _hasSize(plant: AssertionPlant<Collection<*>>, size: Int): Assertion =
    ExpectImpl.collection.size(plant) { toBe(size) }

fun _size(plant: AssertionPlant<Collection<*>>, assertionCreator: Assert<Int>.() -> Unit) =
    ExpectImpl.collector.collect(plant) { property(Collection<*>::size, assertionCreator) }
