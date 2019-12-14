package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableDomain<O, T : Comparable<O>> : ExpectDomain<T> {
    fun isLessThan(expected: O): Assertion
    fun isLessOrEquals(expected: O): Assertion
    fun isGreaterThan(expected: O): Assertion
    fun isGreaterOrEquals(expected: O): Assertion
}
