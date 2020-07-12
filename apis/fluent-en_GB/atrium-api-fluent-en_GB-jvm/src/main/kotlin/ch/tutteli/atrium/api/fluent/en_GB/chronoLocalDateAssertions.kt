@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.chrono.ChronoLocalDate

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is before the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isBefore(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is before or equal the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isBeforeOrEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is after the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isAfter(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is after or equal the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isAfterOrEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is equal to the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isEqual(expected) }
