package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * A dummy implementation of [IComparableAssertions] which should be replaced by an actual implementation.
 */
object ComparableAssertions: IComparableAssertions {
    override fun <T1 : Comparable<T2>, T2: Any?> isLessThan(plant: AssertionPlant<T1>, expected: T2): Assertion
        = throwUnsupportedOperationException()
    override fun <T1 : Comparable<T2>, T2: Any?> isLessOrEquals(plant: AssertionPlant<T1>, expected: T2): Assertion
        = throwUnsupportedOperationException()
    override fun <T1 : Comparable<T2>, T2: Any?> isGreaterThan(plant: AssertionPlant<T1>, expected: T2): Assertion
        = throwUnsupportedOperationException()
    override fun <T1 : Comparable<T2>, T2: Any?> isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2): Assertion
        = throwUnsupportedOperationException()
}
