package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._toBeWithErrorTolerance
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] without any error tolerance.
 *
 * It effectively delegates to `toBeWithErrorTolerance(expected, 0.0f)`.
 * You might want to use [toBeWithErrorTolerance] instead and specify a tolerance which suits your needs.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Float>.toBe(expected: Float)
    = toBeWithErrorTolerance(expected, 0.0f)

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] without any error tolerance.
 *
 * It effectively delegates to `toBeWithErrorTolerance(expected, 0.0)`.
 * You might want to use [toBeWithErrorTolerance] instead and specify a tolerance which suits your needs.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Double>.toBe(expected: Double)
    = toBeWithErrorTolerance(expected, 0.0)

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float)
    = addAssertion(_toBeWithErrorTolerance(this, expected, tolerance))

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double)
    = addAssertion(_toBeWithErrorTolerance(this, expected, tolerance))

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<BigDecimal>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal)
    = addAssertion(_toBeWithErrorTolerance(this, expected, tolerance))
