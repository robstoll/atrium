package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._isNumericallyEqualTo
import ch.tutteli.atrium.assertions._toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is (equal to) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [AssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.ist(expected: T)
    = addAssertion(_toBe(this, expected, this::istNumerischGleichWie.name))

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
    = addAssertion(_isNumericallyEqualTo(this, expected))
