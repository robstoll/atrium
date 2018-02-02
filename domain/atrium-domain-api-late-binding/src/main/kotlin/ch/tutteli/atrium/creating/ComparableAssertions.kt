package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * A dummy implementation of [IComparableAssertions] which should be replaced by an actual implementation.
 */
object ComparableAssertions: IComparableAssertions {
    override fun <T : Comparable<T>> isLessThan(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Comparable<T>> isLessOrEquals(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Comparable<T>> isGreaterThan(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Comparable<T>> isGreaterOrEquals(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
}
