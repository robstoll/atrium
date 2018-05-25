package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun <T : Map<*, *>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = AssertImpl.collector.collect(plant) {
        property(Map<*, *>::size) { toBe(size) }
    }

fun <T : Map<*, *>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isEmpty() }
        .create(DescriptionBasic.IS, RawString.create(EMPTY))

fun <T : Map<*, *>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isNotEmpty() }
        .create(DescriptionBasic.IS_NOT, RawString.create(EMPTY))
