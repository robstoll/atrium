package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._isNotNumericallyEqualTo
import ch.tutteli.atrium.assertions._isNumericallyEqualTo
import ch.tutteli.atrium.assertions._toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is (equal to) [expected].
 *
 * Most of the time you want to use [isNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN) toBe BigDecimal("10.0")` does not hold
 * - `assert(BigDecimal.TEN) isNumericallyEqualTo BigDecimal("10.0")` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : BigDecimal> Assert<T>.toBe(expected: T)
    = addAssertion(_toBe(this, expected, this::isNumericallyEqualTo.name))

@Suppress("unused")
@Deprecated("Use `isNotNumericallyEqualTo` if you expect that the following assertion is wrong:\n" +
    "`assert(BigDecimal(\"10\") notToBe BigDecimal(\"10.0\")`\n" +
    "However, if you expect it to hold, then the `notToBe` function is the right one for you, use the overload with `Any`.",
    ReplaceWith("notToBe(expected as Any)"))
infix fun <T : BigDecimal> Assert<T>.notToBe(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal::equals compares also BigDecimal::scale, which you might not be aware of.\n" +
    "If you know it and still want to use `notToBe` instead of `isNotNumericallyEqualTo` then use the overload with `Any`.")


/**
 * Makes the assertion that [AssertionPlant.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [toBe] because [toBe] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN) toBe BigDecimal("10.0")` does not hold
 * - `assert(BigDecimal.TEN) isNumericallyEqualTo BigDecimal("10.0")` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : BigDecimal> Assert<T>.isNumericallyEqualTo(expected: T)
    = addAssertion(_isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 * Most of the time you want to use this function instead of [notToBe] because [notToBe] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN) toBe BigDecimal("10.0")` holds.
 * - `assert(BigDecimal.TEN) isNumericallyEqualTo BigDecimal("10.0")`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : BigDecimal> Assert<T>.isNotNumericallyEqualTo(expected: T)
    = addAssertion(_isNotNumericallyEqualTo(this, expected))
