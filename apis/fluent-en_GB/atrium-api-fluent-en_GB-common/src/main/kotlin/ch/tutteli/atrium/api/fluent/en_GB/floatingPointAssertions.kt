package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain

/**
 * Expects that the subject of the assertion (a [Float]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of the assertion` - [expected] | ≤ [tolerance]
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Expect<Float> =
    addAssertion(_domain.toBeWithErrorTolerance(expected, tolerance))

/**
 * Expects that the subject of the assertion  (a [Double]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of the assertion` - [expected] | ≤ [tolerance]
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Expect<Double> =
    addAssertion(_domain.toBeWithErrorTolerance(expected, tolerance))
