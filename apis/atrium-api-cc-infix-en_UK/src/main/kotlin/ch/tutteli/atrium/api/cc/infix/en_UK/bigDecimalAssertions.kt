package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._isNumericallyEqual
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is numerically (hence ignoring precision) equal to [expected].
 *
 * Most of the time you want to use this function instead of [toBe] which also compares precision.
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN).toBe(BigDecimal("10.0"))` does not hold
 * - `assert(BigDecimal.TEN).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : BigDecimal> Assert<T>.isNumericallyEqualTo(expected: T)
    = addAssertion(_isNumericallyEqual(this, expected))
