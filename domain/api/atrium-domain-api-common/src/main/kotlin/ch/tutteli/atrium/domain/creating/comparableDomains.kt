package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableDomain<O, T : Comparable<O>> : ComparableOnlyDomain<O, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence]
 * excluding the assertion functions which are defined on domains of  super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableOnlyDomain<O, T : Comparable<O>> : ExpectDomain<T> {
    fun isLessThan(expected: O): Assertion
    fun isLessOrEquals(expected: O): Assertion
    fun isGreaterThan(expected: O): Assertion
    fun isGreaterOrEquals(expected: O): Assertion
}
