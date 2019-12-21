@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import java.math.BigDecimal

internal class BigDecimalDomainImpl<T : BigDecimal>(
    bigDecimalDomainSubImpl: BigDecimalSubDomain<T>,
    anyDomain: AnyDomain<T>
) : BigDecimalDomain<T>, BigDecimalSubDomain<T> by bigDecimalDomainSubImpl, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = bigDecimalDomainSubImpl.expect
}

internal class BigDecimalSubDomainImpl<T : BigDecimal>(
    override val expect: Expect<T>

) : BigDecimalSubDomain<T> {
    override fun toBeWithErrorTolerance(expected: T, tolerance: T): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)

    override fun isNumericallyEqualTo(expected: T): Assertion =
        bigDecimalAssertions.isNumericallyEqualTo(expect, expected)

    override fun isNotNumericallyEqualTo(expected: T): Assertion =
        bigDecimalAssertions.isNotNumericallyEqualTo(expect, expected)

    override fun isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion =
        bigDecimalAssertions.isEqualIncludingScale(expect, expected, nameOfIsNumericallyEqualTo)

    override fun isNotEqualIncludingScale(expected: T): Assertion =
        bigDecimalAssertions.isNotEqualIncludingScale(expect, expected)
}
