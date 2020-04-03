@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.bigDecimal
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import java.math.BigDecimal

/**
 * Deprecated as it would compare the subject against [expected] including scale
 * -- many developers are not aware of that.
 *
 * Use [isNumericallyEqualTo] if you expect that the following assertion holds:
 * ```
 * expect(BigDecimal("10").toBe(BigDecimal("10.0"))
 * ```
 * However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use [isEqualIncludingScale].
 */
@Deprecated(
    "Use `isNumericallyEqualTo` if you expect that the following assertion holds:\n" +
        "`expect(BigDecimal(\"10\")).toBe(BigDecimal(\"10.0\"))`\n" +
        "However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use `isEqualIncludingScale`.",
    ReplaceWith("isNumericallyEqualTo(expected) or isEqualIncludingScale(expected)")
)
@Suppress("UNUSED_PARAMETER", "unused")
infix fun <T : BigDecimal> Expect<T>.toBe(expected: T): Nothing = throw PleaseUseReplacementException(
    "BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
        "If you know it and want that `scale` is included in the comparison, then use `isEqualIncludingScale`."
)

@Suppress("UNUSED_PARAMETER", "unused")
@JvmName("toBeNullable")
@Deprecated(
    "Use `isNumericallyEqualTo` if you expect that the following assertion holds:\n" +
        "`expect(BigDecimal(\"10\")).toBe(BigDecimal(\"10.0\"))`\n" +
        "However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use `isEqualIncludingScale`.",
    ReplaceWith("isNumericallyEqualTo(expected) or isEqualIncludingScale(expected)")
)
infix fun <T : BigDecimal?> Expect<T>.toBe(expected: T): Nothing = throw PleaseUseReplacementException(
    "BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
        "If you know it and want that `scale` is included in the comparison, then use `isEqualIncludingScale`."
)

/**
 * Expects that the subject of the assertion (a [BigDecimal]) is `null`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 */
@JvmName("toBeNull")
infix fun <T : BigDecimal> Expect<T?>.toBe(expected: Nothing?): Expect<T?> =
    addAssertion(ExpectImpl.any.toBe(this, expected))

/**
 * Deprecated as it would compare the subject against [expected] including scale
 * -- many developers are not aware of that.
 *
 * Use [isNotNumericallyEqualTo] if you expect that the following assertion is wrong:
 * ```
 * expect(BigDecimal("10").notToBe(BigDecimal("10.0"))
 * ```
 * However, if you expect it to be wrong (because `BigDecimal.scale` differ), then use [isNotEqualIncludingScale].
 */
@Deprecated(
    "Use `isNotNumericallyEqualTo` if you expect that the following assertion is wrong:\n" +
        "`expect(BigDecimal(\"10\")).notToBe(BigDecimal(\"10.0\"))`\n" +
        "However, if you expect it to hold (because `BigDecimal.scale` differ), then use `isNotEqualIncludingScale`.",
    ReplaceWith("isNotNumericallyEqualTo(expected) or isNotEqualIncludingScale(expected)")
)
@Suppress("UNUSED_PARAMETER", "unused")
infix fun <T : BigDecimal> Expect<T>.notToBe(expected: T): Nothing = throw PleaseUseReplacementException(
    "BigDecimal.equals() compares also BigDecimal.scale, which you might not be aware of.\n" +
        "If you know it and want that `scale` is included in the comparison, then use `isNotEqualIncludingScale`."
)

/**
 * Expects that the subject of the assertion (a [BigDecimal]) is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [isEqualIncludingScale] because
 * [isEqualIncludingScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `expect(BigDecimal("10")).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `expect(BigDecimal("10")).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 */
infix fun <T : BigDecimal> Expect<T>.isNumericallyEqualTo(expected: T) =
    addAssertion(ExpectImpl.bigDecimal.isNumericallyEqualTo(this, expected))

/**
 * Expects that the subject of the assertion (a [BigDecimal]) is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 *
 * Most of the time you want to use this function instead of [isNotEqualIncludingScale] because
 * [isNotEqualIncludingScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `expect(BigDecimal("10")).isNotEqualIncludingScale(BigDecimal("10.0"))` holds.
 * - `expect(BigDecimal("10")).isNotNumericallyEqualTo(BigDecimal("10.0"))`  does not hold.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 */
infix fun <T : BigDecimal> Expect<T>.isNotNumericallyEqualTo(expected: T) =
    addAssertion(ExpectImpl.bigDecimal.isNotNumericallyEqualTo(this, expected))


/**
 * Expects that the subject of the assertion (a [BigDecimal]) is equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [isNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `expect(BigDecimal("10")).isEqualIncludingScale(BigDecimal("10.0"))` does not hold.
 * - `expect(BigDecimal("10")).isNumericallyEqualTo(BigDecimal("10.0"))` holds.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 */
infix fun <T : BigDecimal> Expect<T>.isEqualIncludingScale(expected: T) =
    addAssertion(ExpectImpl.bigDecimal.isEqualIncludingScale(this, expected, this::isNumericallyEqualTo.name))

/**
 * Expects that the subject of the assertion (a [BigDecimal]) is not equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [isNotNumericallyEqualTo] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `expect(BigDecimal("10")).isNotEqualIncludingScale(BigDecimal("10.0"))` holds.
 * - `expect(BigDecimal("10")).isNotNumericallyEqualTo(BigDecimal("10.0"))`  does not hold.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 */
infix fun <T : BigDecimal> Expect<T>.isNotEqualIncludingScale(expected: T) =
    addAssertion(ExpectImpl.bigDecimal.isNotEqualIncludingScale(this, expected))
