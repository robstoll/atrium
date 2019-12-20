@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Float].
 */
val Expect<Float>._domain: FloatDomain
    get() = FloatDomainImpl(FloatOnlyDomainImpl(this), AnyDomainImpl(this))

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Double].
 */
val Expect<Double>._domain: DoubleDomain
    get() = DoubleDomainImpl(DoubleOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Represents a base interface for floating point like types which thus share common assertion functions.
 */
interface FloatingPointDomain<T> : ExpectDomain<T> {
    fun toBeWithErrorTolerance(expected: T, tolerance: T): Assertion
}


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Float],
 * which an implementation of the domain of Atrium has to provide.
 */
interface FloatDomain : FloatOnlyDomain, AnyDomain<Float>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Float]
 * excluding the assertion functions which are defined on domains of  super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface FloatOnlyDomain : FloatingPointDomain<Float>


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Double],
 * which an implementation of the domain of Atrium has to provide.
 */
interface DoubleDomain : DoubleOnlyDomain, AnyDomain<Double>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Double]
 * excluding the assertion functions which are defined on domains of  super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface DoubleOnlyDomain : FloatingPointDomain<Double>
