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
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeBefore; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeBefore<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isBefore(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeBeforeOrTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeBeforeOrTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isBeforeOrEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeAfter; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAfter<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isAfter(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeAfterOrTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAfterOrTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isAfterOrEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isEqual(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else a [java.time.DateTimeException] will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toBeBefore; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeBefore<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isBefore(expected: String): Expect<T> =
    _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else a [java.time.DateTimeException] will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toBeBeforeOrTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeBeforeOrTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isBeforeOrEqual(expected: String): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else a [java.time.DateTimeException] will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toBeAfter; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAfter<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isAfter(expected: String): Expect<T> =
    _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else a [java.time.DateTimeException] will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toBeAfterOrTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAfterOrTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isAfterOrEqual(expected: String): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else a [java.time.DateTimeException] will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toBeTheSamePointInTimeAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeTheSamePointInTimeAs<T>(expected)")
)
fun <T : ChronoLocalDate> Expect<T>.isEqual(expected: String): Expect<T> =
    _logicAppend { isEqual(expected) }
