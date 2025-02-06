//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeWithErrorTolerance

/**
 * Expects that the subject of `this` expectation (a [Float]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the expectation holds; otherwise it fails.
 * A more mathematical way of expressing the expectation is the following inequality:
 *
 * | `subject of `this` expectation` - [expected] | ≤ [tolerance]
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FloatingPointExpectationSamples.toEqualWithErrorToleranceFloat
 *
 * @since 0.17.0
 */
fun Expect<Float>.toEqualWithErrorTolerance(expected: Float, tolerance: Float): Expect<Float> =
    _coreAppend { toBeWithErrorTolerance(expected, tolerance) }

/**
 * Expects that the subject of `this` expectation  (a [Double]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the expectation holds; otherwise it fails.
 * A more mathematical way of expressing the expectation is the following inequality:
 *
 * | `subject of `this` expectation` - [expected] | ≤ [tolerance]
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FloatingPointExpectationSamples.toEqualWithErrorToleranceDouble
 *
 * @since 0.17.0
 */
fun Expect<Double>.toEqualWithErrorTolerance(expected: Double, tolerance: Double): Expect<Double> =
    _coreAppend { toBeWithErrorTolerance(expected, tolerance) }
