package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.HAS_SIZE
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <T : Collection<*>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = BasicDescriptiveAssertion(HAS_SIZE, size, { plant.subject.size == size })

fun <T : Collection<*>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = BasicDescriptiveAssertion(DescriptionBasic.IS, RawString.create(EMPTY), { plant.subject.isEmpty() })

fun <T : Collection<*>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = BasicDescriptiveAssertion(DescriptionBasic.IS_NOT, RawString.create(EMPTY), { plant.subject.isNotEmpty() })
