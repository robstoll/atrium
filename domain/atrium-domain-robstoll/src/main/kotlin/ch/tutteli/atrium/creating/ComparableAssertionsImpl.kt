package ch.tutteli.atrium.creating

/**
 * Robstoll's implementation of [ComparableAssertions].
 */
class ComparableAssertionsImpl: ComparableAssertions {

    override fun <T1 : Comparable<T2>, T2: Any?> isLessThan(plant: AssertionPlant<T1>, expected: T2)
        = _isLessThan(plant, expected)

    override fun <T1 : Comparable<T2>, T2: Any?> isLessOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = _isLessOrEquals(plant, expected)

    override fun <T1 : Comparable<T2>, T2: Any?> isGreaterThan(plant: AssertionPlant<T1>, expected: T2)
        = _isGreaterThan(plant, expected)

    override fun <T1 : Comparable<T2>, T2: Any?> isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2)
        = _isGreaterOrEquals(plant, expected)
}
