package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.specs.integration.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    @Suppress("DEPRECATION") fun1<BigDecimal, BigDecimal>(Expect<BigDecimal>::toBe),
    Expect<Any>::toBe,
    @Suppress("DEPRECATION") Expect<BigDecimal>::notToBe.name to @Suppress("DEPRECATION") Expect<BigDecimal>::notToBe,
    Expect<Any>::notToBe,
    Expect<BigDecimal>::isNumericallyEqualTo.name to Expect<BigDecimal>::isNumericallyEqualTo,
    Expect<BigDecimal>::isNotNumericallyEqualTo.name to Expect<BigDecimal>::isNotNumericallyEqualTo,
    Expect<BigDecimal>::isEqualIncludingScale.name to Expect<BigDecimal>::isEqualIncludingScale,
    Expect<BigDecimal>::isNotEqualIncludingScale.name to Expect<BigDecimal>::isNotEqualIncludingScale
)
