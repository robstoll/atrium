package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._isNotNumericallyEqualTo
import ch.tutteli.atrium.assertions._isNumericallyEqualTo
import ch.tutteli.atrium.assertions._isEqualIncludingScale
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

@Deprecated("Use `isNumericallyEqualTo` if you expect that the following assertion holds:\n" +
    "`assert(BigDecimal(\"10\").toBe(BigDecimal(\"10.0\"))`\n" +
    "However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use `isEqualIncludingScale`.",
    ReplaceWith("isNumericallyEqualTo(expected) or isEqualIncludingScale(expected)"))
@Suppress("unused")
infix fun <T : BigDecimal> Assert<T>.toBe(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
    "If you know it and want that `scale` is included in the comparison, then use `isEqualIncludingScale`.")

@Deprecated("Use `isNotNumericallyEqualTo` if you expect that the following assertion is wrong:\n" +
    "`assert(BigDecimal(\"10\") notToBe BigDecimal(\"10.0\")`\n" +
    "However, if you expect it to hold (because `BigDecimal.scale` differ), then use the overload of `notToBe`with `Any`.",
    ReplaceWith("isNotNumericallyEqualTo(expected) or notToBe(expected as Any)"))
@Suppress("unused")
infix fun <T : BigDecimal> Assert<T>.notToBe(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
    "If you know it and want that `scale` is included in the comparison, then use the overload of `notToBe`with `Any`.")


/**
 * Makes the assertion that [AssertionPlant.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [isEqualIncludingScale] because
 * [isEqualIncludingScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `assert(BigDecimal.TEN).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isNumericallyEqualTo(expected: T)
    = addAssertion(_isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 * Most of the time you want to use this function instead of [notToBe] because [notToBe] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN).toBe(BigDecimal("10.0"))` holds.
 * - `assert(BigDecimal.TEN).isNumericallyEqualTo(BigDecimal("10.0"))`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isNotNumericallyEqualTo(expected: T)
    = addAssertion(_isNotNumericallyEqualTo(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] is (equal to) [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [isNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal.TEN).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `assert(BigDecimal.TEN).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isEqualIncludingScale(expected: T)
    = addAssertion(_isEqualIncludingScale(this, expected, this::isNumericallyEqualTo.name))
