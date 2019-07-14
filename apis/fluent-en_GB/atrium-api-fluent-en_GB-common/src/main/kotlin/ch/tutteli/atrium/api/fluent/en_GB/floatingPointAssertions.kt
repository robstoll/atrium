@file:JvmMultifileClass
@file:JvmName("FloatingPointAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Expects that the subject of the assertion is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of the assertion` - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float) =
    addAssertion(ExpectImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))

/**
 * Expects that the subject of the assertion is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of the assertion` - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double) =
    addAssertion(ExpectImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))
