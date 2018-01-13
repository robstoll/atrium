package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._isNumericallyEqual
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is numerically (hence ignoring precision) equal to [expected].
 *
 * Most of the time you want to use this function instead of [ist] which also compares precision.
 * Following the two functions compared:
 * - `esGilt(BigDecimal.TEN).ist(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal.TEN).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNumerischGleichWie(expected: T)
    = addAssertion(_isNumericallyEqual(this, expected))
