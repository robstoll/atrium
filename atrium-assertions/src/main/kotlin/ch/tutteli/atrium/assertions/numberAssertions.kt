package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionNumberAssertion.*
import ch.tutteli.atrium.creating.AssertionPlant

fun <T> _isLessThan(plant: AssertionPlant<T>, expected: T): Assertion where T : Number, T : Comparable<T>
    = BasicDescriptiveAssertion(IS_LESS_THAN, expected, { plant.subject < expected })

fun <T> _isLessOrEquals(plant: AssertionPlant<T>, expected: T): Assertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicDescriptiveAssertion(IS_LESS_OR_EQUALS, expected, { plant.subject <= expected })

fun <T> _isGreaterThan(plant: AssertionPlant<T>, expected: T): Assertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicDescriptiveAssertion(IS_GREATER_THAN, expected, { plant.subject > expected })

fun <T> _isGreaterOrEquals(plant: AssertionPlant<T>, expected: T): Assertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicDescriptiveAssertion(IS_GREATER_OR_EQUALS, expected, { plant.subject >= expected })
