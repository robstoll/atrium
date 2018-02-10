@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

object ComparableAssertionsBuilder : IComparableAssertions {
    override inline fun <T1 : Comparable<T2>, T2: Any?> isLessThan(plant: AssertionPlant<T1>, expected: T2)
        = ComparableAssertions.isLessThan(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isLessOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = ComparableAssertions.isLessOrEquals(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isGreaterThan(plant: AssertionPlant<T1>, expected: T2)
        = ComparableAssertions.isGreaterThan(plant, expected)

    override inline fun <T1 : Comparable<T2>, T2: Any?> isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = ComparableAssertions.isGreaterOrEquals(plant, expected)

}
