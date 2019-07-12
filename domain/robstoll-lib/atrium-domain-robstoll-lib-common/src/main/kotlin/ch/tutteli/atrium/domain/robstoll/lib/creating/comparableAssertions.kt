package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*

fun <T1 : Comparable<T2>, T2: Any?> _isLessThan(plant: AssertionPlant<T1>, expected: T2)
    = AssertImpl.builder.createDescriptive(plant, IS_LESS_THAN, expected) { it < expected }

fun <T1 : Comparable<T2>, T2: Any?> _isLessOrEquals(plant: AssertionPlant<T1>, expected: T2)
    = AssertImpl.builder.createDescriptive(plant, IS_LESS_OR_EQUALS, expected) { it <= expected }

fun <T1 : Comparable<T2>, T2: Any?> _isGreaterThan(plant: AssertionPlant<T1>, expected: T2)
    = AssertImpl.builder.createDescriptive(plant, IS_GREATER_THAN, expected) { it > expected }

fun <T1 : Comparable<T2>, T2: Any?> _isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2)
    = AssertImpl.builder.createDescriptive(plant, IS_GREATER_OR_EQUALS, expected) { it >= expected }
