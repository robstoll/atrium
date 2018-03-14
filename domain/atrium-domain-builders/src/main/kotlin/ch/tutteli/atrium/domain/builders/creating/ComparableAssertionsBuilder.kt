@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.ComparableAssertions
import ch.tutteli.atrium.domain.creating.comparableAssertions

object ComparableAssertionsBuilder : ComparableAssertions {
    override inline fun <T1 : Comparable<T2>, T2: Any?> isLessThan(plant: AssertionPlant<T1>, expected: T2)
        = comparableAssertions.isLessThan(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isLessOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = comparableAssertions.isLessOrEquals(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isGreaterThan(plant: AssertionPlant<T1>, expected: T2)
        = comparableAssertions.isGreaterThan(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = comparableAssertions.isGreaterOrEquals(plant, expected)
}
