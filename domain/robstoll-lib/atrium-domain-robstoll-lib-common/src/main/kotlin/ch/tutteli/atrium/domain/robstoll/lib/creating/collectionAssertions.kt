package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun _hasSize(plant: AssertionPlant<Collection<*>>, size: Int): Assertion
    = AssertImpl.collection.size(plant){ toBe(size) }

fun _isEmpty(plant: AssertionPlant<Collection<*>>): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.create(EMPTY)) { plant.subject.isEmpty() }

fun _isNotEmpty(plant: AssertionPlant<Collection<*>>): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionBasic.IS_NOT, RawString.create(EMPTY)) { plant.subject.isNotEmpty() }

fun _size(plant: AssertionPlant<Collection<*>>, assertionCreator: Assert<Int>.() -> Unit)
    = AssertImpl.collector.collect(plant){ property(Collection<*>::size, assertionCreator) }
