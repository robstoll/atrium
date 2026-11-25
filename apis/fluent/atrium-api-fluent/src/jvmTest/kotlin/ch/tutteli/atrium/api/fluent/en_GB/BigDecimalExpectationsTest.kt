package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractBigDecimalExpectationsTest(
    @Suppress("DEPRECATION") fun1<BigDecimal, BigDecimal>(Expect<BigDecimal>::toEqual),
    @Suppress("DEPRECATION") fun1<BigDecimal?, BigDecimal?>(Expect<BigDecimal?>::toEqual).withNullableSuffix(),
    fun1<BigDecimal?, Nothing?>(Expect<BigDecimal?>::toEqual).withNullableSuffix(),
    Expect<Any>::toEqual,
    @Suppress("DEPRECATION") (Expect<BigDecimal>::notToEqual.name to Expect<BigDecimal>::notToEqual),
    Expect<Any>::notToEqual,
    Expect<BigDecimal>::toEqualNumerically.name to Expect<BigDecimal>::toEqualNumerically,
    Expect<BigDecimal>::notToEqualNumerically.name to Expect<BigDecimal>::notToEqualNumerically,
    Expect<BigDecimal>::toEqualIncludingScale.name to Expect<BigDecimal>::toEqualIncludingScale,
    Expect<BigDecimal>::notToEqualIncludingScale.name to Expect<BigDecimal>::notToEqualIncludingScale
) {
    @Test
    fun choosesTheCorrectOverloadIfNullIsPassed() {
        expect(null as BigDecimal?).toEqual(null)
    }
}
