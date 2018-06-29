package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import java.math.BigDecimal

@Deprecated("Use `isNumericallyEqualTo` if you expect that the following assertion holds:\n" +
    "`assert(BigDecimal(\"10\").toBe(BigDecimal(\"10.0\"))`\n" +
    "However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use `isEqualIncludingScale`.",
    ReplaceWith("isNumericallyEqualTo(expected) or isEqualIncludingScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.toBe(expected: T): Nothing
    = throw PleaseUseReplacementException("BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
    "If you know it and want that `scale` is included in the comparison, then use `isEqualIncludingScale`.")

@Deprecated("Use `isNotNumericallyEqualTo` if you expect that the following assertion is wrong:\n" +
    "`assert(BigDecimal(\"10\") notToBe BigDecimal(\"10.0\")`\n" +
    "However, if you expect it to hold (because `BigDecimal.scale` differ), then use `isNotEqualIncludingScale`.",
    ReplaceWith("isNotNumericallyEqualTo(expected) or isNotEqualIncludingScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.notToBe(expected: T): Nothing
    = throw PleaseUseReplacementException("BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
    "If you know it and want that `scale` is included in the comparison, then use `isNotEqualIncludingScale`.")


/**
 * Makes the assertion that [AssertionPlant.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [isEqualIncludingScale] because
 * [isEqualIncludingScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `assert(BigDecimal("10")).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isNumericallyEqualTo(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 *
 * Most of the time you want to use this function instead of [isNotEqualIncludingScale] because
 * [isNotEqualIncludingScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).isNotEqualIncludingScale(BigDecimal("10.0"))` holds.
 * - `assert(BigDecimal("10")).isNotNumericallyEqualTo(BigDecimal("10.0"))`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isNotNumericallyEqualTo(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotNumericallyEqualTo(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [isNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `assert(BigDecimal("10")).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isEqualIncludingScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isEqualIncludingScale(this, expected, this::isNumericallyEqualTo.name))

/**
 * Makes the assertion that [AssertionPlant.subject] is not equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [isNotNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).isNotEqualIncludingScale(BigDecimal("10.0"))` holds.
 * - `assert(BigDecimal("10")).isNotNumericallyEqualTo(BigDecimal("10.0"))`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.isNotEqualIncludingScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotEqualIncludingScale(this, expected))
