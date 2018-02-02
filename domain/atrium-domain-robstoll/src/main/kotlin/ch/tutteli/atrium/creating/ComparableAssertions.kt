package ch.tutteli.atrium.creating

/**
 * Robstoll's implementation of [IComparableAssertions].
 */
object ComparableAssertions: IComparableAssertions {
    override fun <T : Comparable<T>> isLessThan(plant: AssertionPlant<T>, expected: T)
        = _isLessThan(plant, expected)
    override fun <T : Comparable<T>> isLessOrEquals(plant: AssertionPlant<T>, expected: T)
        = _isLessOrEquals(plant, expected)
    override fun <T : Comparable<T>> isGreaterThan(plant: AssertionPlant<T>, expected: T)
        = _isGreaterThan(plant, expected)
    override fun <T : Comparable<T>> isGreaterOrEquals(plant: AssertionPlant<T>, expected: T)
        = _isGreaterOrEquals(plant, expected)
}
