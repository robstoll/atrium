package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<BigDecimal>::isNumericallyEqualTo.name to AssertionPlant<BigDecimal>::isNumericallyEqualTo,
    AssertionPlant<BigDecimal>::isNotNumericallyEqualTo.name to AssertionPlant<BigDecimal>::isNotNumericallyEqualTo,
    AssertionPlant<BigDecimal>::toBe.name to AssertionPlant<BigDecimal>::toBe,
    @Suppress("DEPRECATION") AssertionPlant<BigDecimal>::notToBe.name to @Suppress("DEPRECATION") AssertionPlant<BigDecimal>::notToBe,
    AssertionPlant<Any>::notToBe
)
