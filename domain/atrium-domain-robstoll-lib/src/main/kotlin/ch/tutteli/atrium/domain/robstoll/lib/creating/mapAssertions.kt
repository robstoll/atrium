package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun <T : Map<*, *>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = AssertImpl.collector.collect(plant) {
        AssertImpl.feature.property(it, it.subject::size) {
            addAssertion(AssertImpl.any.toBe(this, size))
        }
    }

fun <T : Map<*, *>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive.create(DescriptionBasic.IS, RawString.create(EMPTY), { plant.subject.isEmpty() })

fun <T : Map<*, *>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive.create(DescriptionBasic.IS_NOT, RawString.create(EMPTY), { plant.subject.isNotEmpty() })
