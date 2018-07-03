package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.integration.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    @Suppress("DEPRECATION") Assert<BigDecimal>::ist.name to @Suppress("DEPRECATION") Assert<BigDecimal>::ist,
    Assert<Any>::ist,
    @Suppress("DEPRECATION") Assert<BigDecimal>::istNicht.name to @Suppress("DEPRECATION") Assert<BigDecimal>::istNicht,
    Assert<Any>::istNicht,
    Assert<BigDecimal>::istNumerischGleichWie.name to Assert<BigDecimal>::istNumerischGleichWie,
    Assert<BigDecimal>::istNichtNumerischGleichWie.name to Assert<BigDecimal>::istNichtNumerischGleichWie,
    Assert<BigDecimal>::istGleichInklusiveScale.name to Assert<BigDecimal>::istGleichInklusiveScale,
    Assert<BigDecimal>::istNichtGleichInklusiveScale.name to Assert<BigDecimal>::istNichtGleichInklusiveScale
)
