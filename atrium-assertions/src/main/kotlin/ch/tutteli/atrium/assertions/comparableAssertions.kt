package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.ComparableAssertions

@Deprecated("use ComparableAssertions.isLessThan instead, will be removed with 1.0.0", ReplaceWith("ComparableAssertions.isLessThan(plant, expected)"))
fun <T : Comparable<T>> _isLessThan(plant: AssertionPlant<T>, expected: T)
    = ComparableAssertions.isLessThan(plant, expected)

@Deprecated("use ComparableAssertions.isLessOrEquals instead, will be removed with 1.0.0", ReplaceWith("ComparableAssertions.isLessOrEquals(plant, expected)"))
fun <T : Comparable<T>> _isLessOrEquals(plant: AssertionPlant<T>, expected: T)
    = ComparableAssertions.isLessOrEquals(plant, expected)

@Deprecated("use ComparableAssertions.isGreaterThan instead, will be removed with 1.0.0", ReplaceWith("ComparableAssertions.isGreaterThan(plant, expected)"))
fun <T : Comparable<T>> _isGreaterThan(plant: AssertionPlant<T>, expected: T)
    = ComparableAssertions.isGreaterThan(plant, expected)

@Deprecated("use ComparableAssertions.isGreaterOrEquals instead, will be removed with 1.0.0", ReplaceWith("ComparableAssertions.isGreaterOrEquals(plant, expected)"))
fun <T : Comparable<T>> _isGreaterOrEquals(plant: AssertionPlant<T>, expected: T)
    = ComparableAssertions.isGreaterOrEquals(plant, expected)
