@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.chrono.ChronoLocalDate

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeBefore(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeBefore(expected: String): Expect<T> =
    _logicAppend { isBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeAfterOrTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeAfterOrTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeAfter(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @since 0.17.0
 */
fun <T : ChronoLocalDate> Expect<T>.toBeAfter(expected: String): Expect<T> =
    _logicAppend { isAfter(expected) }
