package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import java.math.BigDecimal

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [Assert.subject][AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [Assert.subject][AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toBeWithErrorTolerance(expected, tolerance)"))
fun Assert<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float)
    = addAssertion(AssertImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [Assert.subject][AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [Assert.subject][AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toBeWithErrorTolerance(expected, tolerance)"))
fun Assert<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double)
    = addAssertion(AssertImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [Assert.subject][AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [Assert.subject][AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toBeWithErrorTolerance(expected, tolerance)"))
fun Assert<BigDecimal>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal)
    = addAssertion(AssertImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))
