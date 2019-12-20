@file:Suppress(
    "ObjectPropertyName",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.BigDecimalDomainImpl
import ch.tutteli.atrium.domain.creating.impl.BigDecimalOnlyDomainImpl
import java.math.BigDecimal

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [BigDecimal].
 */
val <T : BigDecimal> Expect<T>._domain: BigDecimalDomain<T>
    get() = BigDecimalDomainImpl(BigDecimalOnlyDomainImpl(this), AnyDomainImpl(this))

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [BigDecimal],
 * which an implementation of the domain of Atrium has to provide.
 */
interface BigDecimalDomain<T> : BigDecimalOnlyDomain<T>, AnyDomain<T>


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [BigDecimal]
 * excluding the assertion functions which are defined on domains of  super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface BigDecimalOnlyDomain<T> : FloatingPointDomain<T> {
    fun isNumericallyEqualTo(expected: T): Assertion
    fun isNotNumericallyEqualTo(expected: T): Assertion
    fun isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    fun isNotEqualIncludingScale(expected: T): Assertion
}

