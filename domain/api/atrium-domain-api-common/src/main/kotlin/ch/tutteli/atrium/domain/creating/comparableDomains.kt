@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.AnyInclNullableDomainImpl
import ch.tutteli.atrium.domain.creating.impl.ComparableDomainImpl
import ch.tutteli.atrium.domain.creating.impl.ComparableSubDomainImpl

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Comparable];
 * i.e. it returns a [ComparableDomain] for this [Expect].
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
val <O, T : Comparable<O>> Expect<T>._domain: ComparableDomain<O, T>
    get() = ComparableDomainImpl(
        ComparableSubDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        AnyDomainImpl(this, AnyInclNullableDomainImpl(this))
    )


/**
 * Represents the [ExpectDomain] whose type extends [Comparable];
 * i.e. the subject of the underlying [expect] has such a type.
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableDomain<O, T : Comparable<O>> : ComparableSubDomain<O, T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Comparable]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Comparable] (e.g. the functions of the [AnyDomain]).
 *
 * @param T the type which is able to compare itself against another type [O]
 * @param O the other type to which [T] is comparable
 */
interface ComparableSubDomain<O, T : Comparable<O>> : ExpectDomain<T> {
    fun isLessThan(expected: O): Assertion
    fun isLessOrEquals(expected: O): Assertion
    fun isGreaterThan(expected: O): Assertion
    fun isGreaterOrEquals(expected: O): Assertion
}
