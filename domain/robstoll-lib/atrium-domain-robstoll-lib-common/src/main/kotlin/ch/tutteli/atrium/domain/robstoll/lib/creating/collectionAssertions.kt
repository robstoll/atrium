package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun _isEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion
    = AssertImpl.builder.createDescriptive(subjectProvider, DescriptionBasic.IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion
    = AssertImpl.builder.createDescriptive(subjectProvider, DescriptionBasic.IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun _hasSize(plant: AssertionPlant<Collection<*>>, size: Int): Assertion
    = AssertImpl.collection.size(plant){ toBe(size) }

fun _size(plant: AssertionPlant<Collection<*>>, assertionCreator: Assert<Int>.() -> Unit)
    = AssertImpl.collector.collect(plant){ property(Collection<*>::size, assertionCreator) }
