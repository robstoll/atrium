package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<BigDecimal>::istNumerischGleichWie.name to AssertionPlant<BigDecimal>::istNumerischGleichWie,
    AssertionPlant<BigDecimal>::istNichtNumerischGleichWie.name to AssertionPlant<BigDecimal>::istNichtNumerischGleichWie,
    AssertionPlant<BigDecimal>::ist.name to AssertionPlant<BigDecimal>::ist,
    @Suppress("DEPRECATION") AssertionPlant<BigDecimal>::istNicht.name to @Suppress("DEPRECATION") AssertionPlant<BigDecimal>::istNicht,
    AssertionPlant<Any>::istNicht
)
