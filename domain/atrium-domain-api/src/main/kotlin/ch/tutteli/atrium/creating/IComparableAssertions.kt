package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IComparableAssertions {
    fun <T : Comparable<T>> isLessThan(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Comparable<T>> isLessOrEquals(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Comparable<T>> isGreaterThan(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Comparable<T>> isGreaterOrEquals(plant: AssertionPlant<T>, expected: T): Assertion
}
