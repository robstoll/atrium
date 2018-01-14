package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    Assert<BigDecimal>::istNumerischGleichWie.name to Assert<BigDecimal>::istNumerischGleichWie,
    Assert<BigDecimal>::istNichtNumerischGleichWie.name to Assert<BigDecimal>::istNichtNumerischGleichWie,
    Assert<BigDecimal>::ist.name to Assert<BigDecimal>::ist,
    @Suppress("DEPRECATION") Assert<BigDecimal>::istNicht.name to @Suppress("DEPRECATION") Assert<BigDecimal>::istNicht,
    Assert<Any>::istNicht
)
