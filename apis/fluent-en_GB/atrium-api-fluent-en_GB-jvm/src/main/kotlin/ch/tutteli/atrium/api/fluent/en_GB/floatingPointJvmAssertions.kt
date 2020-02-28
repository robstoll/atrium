@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
@file:JvmMultifileClass
@file:JvmName("FloatingPointAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import java.math.BigDecimal

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
 * @return This [Expect] to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Expect<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal) =
    addAssertion(ExpectImpl.floatingPoint.toBeWithErrorTolerance(this, expected, tolerance))
