//TODO remove with 0.18.0 - search also for other "will be removed.*0.18.0"
@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
@file:JvmMultifileClass
@file:JvmName("FloatingPointAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.math.BigDecimal

/**
 * Expects that the subject of `this` expectation is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * In detail, It compares the absolute difference between the subject and [expected];
 * as long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | `subject of `this` expectation` - [expected] | ≤ [tolerance]
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Will be removed without replacement with 0.18.0 - there shouldn't be a need for this function as BigDecimal takes care of the problems float/double have.")
fun <T : BigDecimal> Expect<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Expect<T> =
    _logicAppend { toBeWithErrorTolerance(expected, tolerance) }
