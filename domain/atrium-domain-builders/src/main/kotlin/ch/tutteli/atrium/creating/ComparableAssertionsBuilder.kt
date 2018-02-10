@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

object ComparableAssertionsBuilder : IComparableAssertions {
    override inline fun <T : Comparable<T>> isLessThan(plant: AssertionPlant<T>, expected: T)
        = ComparableAssertions.isLessThan(plant, expected)

    override inline fun <T : Comparable<T>> isLessOrEquals(plant: AssertionPlant<T>, expected: T)
        = ComparableAssertions.isLessOrEquals(plant, expected)

    override inline fun <T : Comparable<T>> isGreaterThan(plant: AssertionPlant<T>, expected: T)
        = ComparableAssertions.isGreaterThan(plant, expected)

    override inline fun <T : Comparable<T>> isGreaterOrEquals(plant: AssertionPlant<T>, expected: T)
        = ComparableAssertions.isGreaterOrEquals(plant, expected)

}
