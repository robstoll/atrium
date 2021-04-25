@file:JvmMultifileClass
@file:JvmName("FloatingPointAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeWithErrorTolerance
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Expects that the subject of `this` expectation (a [Float]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of `this` expectation` - [expected] | ≤ [tolerance]
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.FloatingPointAssertionSamples.toBeWithErrorToleranceFloat
 */
@Deprecated("Use toEqualWithErrorTolerance; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEqualWithErrorTolerance<T>(expected, tolerance)"))
fun Expect<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Expect<Float> =
    _logicAppend { toBeWithErrorTolerance(expected, tolerance) }

/**
 * Expects that the subject of `this` expectation  (a [Double]) is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of `this` expectation` - [expected] | ≤ [tolerance]
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.FloatingPointAssertionSamples.toBeWithErrorToleranceDouble
 */
@Deprecated("Use toEqualWithErrorTolerance; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEqualWithErrorTolerance<T>(expected, tolerance)"))
fun Expect<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Expect<Double> =
    _logicAppend { toBeWithErrorTolerance(expected, tolerance) }
