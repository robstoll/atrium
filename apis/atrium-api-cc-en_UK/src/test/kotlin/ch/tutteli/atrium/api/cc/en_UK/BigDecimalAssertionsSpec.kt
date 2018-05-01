package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.integration.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    @Suppress("DEPRECATION") Assert<BigDecimal>::toBe.name to @Suppress("DEPRECATION") Assert<BigDecimal>::toBe,
    Assert<Any>::toBe,
    @Suppress("DEPRECATION") Assert<BigDecimal>::notToBe.name to @Suppress("DEPRECATION") Assert<BigDecimal>::notToBe,
    Assert<Any>::notToBe,
    Assert<BigDecimal>::isNumericallyEqualTo.name to Assert<BigDecimal>::isNumericallyEqualTo,
    Assert<BigDecimal>::isNotNumericallyEqualTo.name to Assert<BigDecimal>::isNotNumericallyEqualTo,
    Assert<BigDecimal>::isEqualIncludingScale.name to Assert<BigDecimal>::isEqualIncludingScale,
    Assert<BigDecimal>::isNotEqualIncludingScale.name to Assert<BigDecimal>::isNotEqualIncludingScale
)
