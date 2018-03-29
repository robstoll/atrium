package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun <T : Map<*, *>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion {
    val collectingPlant = coreFactory.newCollectingPlant{ plant.subject }
    val featurePlant = AssertImpl.feature.property(collectingPlant, collectingPlant.subject::size)
    featurePlant.addAssertion(_toBe(featurePlant, size))
    return collectingPlant.getAssertions()[0]
}

fun <T : Map<*, *>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive.create(DescriptionBasic.IS, RawString.create(EMPTY), { plant.subject.isEmpty() })

fun <T : Map<*, *>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive.create(DescriptionBasic.IS_NOT, RawString.create(EMPTY), { plant.subject.isNotEmpty() })
