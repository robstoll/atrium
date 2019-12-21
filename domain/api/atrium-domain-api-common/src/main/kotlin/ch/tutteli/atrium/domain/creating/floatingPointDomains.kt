@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Float];
 * i.e. it returns a [FloatDomain] for this [Expect].
 */
val Expect<Float>._domain: FloatDomain
    get() = FloatDomainImpl(
        FloatSubDomainImpl(this),
        ComparableDomainImpl(ComparableSubDomainImpl(this), AnyDomainImpl(this))
    )

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Double];
 * i.e. it returns a [DoubleDomain] for this [Expect].
 */
val Expect<Double>._domain: DoubleDomain
    get() = DoubleDomainImpl(
        DoubleSubDomainImpl(this), ComparableDomainImpl(ComparableSubDomainImpl(this), AnyDomainImpl(this))
    )


/**
 * Represents a base interface for floating point like domains which share common assertion functions.
 */
interface FloatingPointDomain<T> : ExpectDomain<T> {
    fun toBeWithErrorTolerance(expected: T, tolerance: T): Assertion
}


/**
 * Represents the [ExpectDomain] whose type extends [Float];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface FloatDomain : FloatSubDomain, ComparableDomain<Float, Float>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Float]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Float] (e.g. the functions of the [AnyDomain]).
 */
interface FloatSubDomain : FloatingPointDomain<Float>


/**
 * Represents the [ExpectDomain] whose type extends [Double];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface DoubleDomain : DoubleSubDomain, ComparableDomain<Double, Double>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Double]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Double] (e.g. the functions of the [AnyDomain]).
 */
interface DoubleSubDomain : FloatingPointDomain<Double>
