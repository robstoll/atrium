package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    Assert<BigDecimal>::isNumericallyEqualTo.name to Assert<BigDecimal>::isNumericallyEqualTo,
    Assert<BigDecimal>::isNotNumericallyEqualTo.name to Assert<BigDecimal>::isNotNumericallyEqualTo,
    Assert<BigDecimal>::toBe.name to Assert<BigDecimal>::toBe,
    @Suppress("DEPRECATION") Assert<BigDecimal>::notToBe.name to @Suppress("DEPRECATION") Assert<BigDecimal>::notToBe,
    Assert<Any>::notToBe
)
