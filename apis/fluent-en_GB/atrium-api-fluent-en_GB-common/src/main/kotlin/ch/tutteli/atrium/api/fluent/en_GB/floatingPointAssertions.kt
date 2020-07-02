@file:JvmMultifileClass
@file:JvmName("FloatingPointAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeWithErrorTolerance
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Expect<Float> =
    _logicAppend { toBeWithErrorTolerance(expected, tolerance) }

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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Expect<Double> =
    _logicAppend { toBeWithErrorTolerance(expected, tolerance) }
