package ch.tutteli.atrium.robstoll.lib.creating

import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*

fun <T1 : Comparable<T2>, T2: Any?> _isLessThan(plant: AssertionPlant<T1>, expected: T2)
    = AssertionBuilder.descriptive.create(IS_LESS_THAN, expected ?: RawString.NULL, { plant.subject < expected })

fun <T1 : Comparable<T2>, T2: Any?> _isLessOrEquals(plant: AssertionPlant<T1>, expected: T2)
    = AssertionBuilder.descriptive.create(IS_LESS_OR_EQUALS, expected ?: RawString.NULL, { plant.subject <= expected })

fun <T1 : Comparable<T2>, T2: Any?> _isGreaterThan(plant: AssertionPlant<T1>, expected: T2)
    = AssertionBuilder.descriptive.create(IS_GREATER_THAN, expected ?: RawString.NULL, { plant.subject > expected })

fun <T1 : Comparable<T2>, T2: Any?> _isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2)
    = AssertionBuilder.descriptive.create(IS_GREATER_OR_EQUALS, expected ?: RawString.NULL, { plant.subject >= expected })
