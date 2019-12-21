@file:Suppress(
    "ObjectPropertyName",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.BigDecimalDomainImpl
import ch.tutteli.atrium.domain.creating.impl.BigDecimalSubDomainImpl
import java.math.BigDecimal

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [BigDecimal];
 * i.e. it returns a [BigDecimalDomain] for this [Expect].
 */
val <T : BigDecimal> Expect<T>._domain: BigDecimalDomain<T>
    get() = BigDecimalDomainImpl(BigDecimalSubDomainImpl(this), AnyDomainImpl(this))

/**
 * Represents the [ExpectDomain] whose type extends [BigDecimal];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface BigDecimalDomain<T : BigDecimal> : BigDecimalSubDomain<T>, AnyDomain<T>


/**f
 * Represents a sub-[ExpectDomain] whose type extends [BigDecimal]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [BigDecimal] (e.g. the functions of the [AnyDomain]).
 */
interface BigDecimalSubDomain<T : BigDecimal> : FloatingPointDomain<T> {
    fun isNumericallyEqualTo(expected: T): Assertion
    fun isNotNumericallyEqualTo(expected: T): Assertion
    fun isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    fun isNotEqualIncludingScale(expected: T): Assertion
}

