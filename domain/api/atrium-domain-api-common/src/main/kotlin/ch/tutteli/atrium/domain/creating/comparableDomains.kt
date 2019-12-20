@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.ComparableDomainImpl
import ch.tutteli.atrium.domain.creating.impl.ComparableOnlyDomainImpl

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Comparable].
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
val <O, T : Comparable<O>> Expect<T>._domain: ComparableDomain<O, T>
    get() = ComparableDomainImpl(ComparableOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableDomain<O, T : Comparable<O>> : ComparableOnlyDomain<O, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Comparable]
 * excluding the assertion functions which are defined on domains of super types
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
