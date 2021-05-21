@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeBefore(
    expected: ChronoZonedDateTime<*>
): Expect<T> = _logicAppend { isBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime] given as [String].
 *
 * The format is composed of {DateTime}{ZoneDesignator}.
 *
 * DateTime:
 * - yyyy-mm-ddThh:mm:ss.SSS (up to 9 digits for nanoseconds)
 * - yyyy-mm-ddThh:mm:ss
 * - yyyy-mm-ddThh:mm
 * - yyyy-mm-dd
 *
 * And for ZoneDesignator:
 * - Z
 * - +hh
 * - +hh:mm
 * - -hh
 * - -hh:mm
 *
 * Here are some examples on how it can look combined
 * - 2020-01-02T03:04:05Z
 * - 2020-01-02T03:04Z
 * - 2020-01-02Z
 * - 2020-01-02+01:30
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeBefore(
    expected: String
): Expect<T> = _logicAppend { isBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(
    expected: ChronoZonedDateTime<*>
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime] given as [String].
 *
 * The format is composed of {DateTime}{ZoneDesignator}.
 *
 * DateTime:
 * - yyyy-mm-ddThh:mm:ss.SSS (up to 9 digits for nanoseconds)
 * - yyyy-mm-ddThh:mm:ss
 * - yyyy-mm-ddThh:mm
 * - yyyy-mm-dd
 *
 * And for ZoneDesignator:
 * - Z
 * - +hh
 * - +hh:mm
 * - -hh
 * - -hh:mm
 *
 * Here are some examples on how it can look combined
 * - 2020-01-02T03:04:05Z
 * - 2020-01-02T03:04Z
 * - 2020-01-02Z
 * - 2020-01-02+01:30
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is equal to the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeTheSamePointInTimeAs(
    expected: ChronoZonedDateTime<*>
): Expect<T> = _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime] given as [String].
 *
 * The format is composed of {DateTime}{ZoneDesignator}.
 *
 * DateTime:
 * - yyyy-mm-ddThh:mm:ss.SSS (up to 9 digits for nanoseconds)
 * - yyyy-mm-ddThh:mm:ss
 * - yyyy-mm-ddThh:mm
 * - yyyy-mm-dd
 *
 * And for ZoneDesignator:
 * - Z
 * - +hh
 * - +hh:mm
 * - -hh
 * - -hh:mm
 *
 * Here are some examples on how it can look combined
 * - 2020-01-02T03:04:05Z
 * - 2020-01-02T03:04Z
 * - 2020-01-02Z
 * - 2020-01-02+01:30
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is after or equal the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeAfterOrTheSamePointInTimeAs(
    expected: ChronoZonedDateTime<*>
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime] given as [String].
 *
 * The format is composed of {DateTime}{ZoneDesignator}.
 *
 * DateTime:
 * - yyyy-mm-ddThh:mm:ss.SSS (up to 9 digits for nanoseconds)
 * - yyyy-mm-ddThh:mm:ss
 * - yyyy-mm-ddThh:mm
 * - yyyy-mm-dd
 *
 * And for ZoneDesignator:
 * - Z
 * - +hh
 * - +hh:mm
 * - -hh
 * - -hh:mm
 *
 * Here are some examples on how it can look combined
 * - 2020-01-02T03:04:05Z
 * - 2020-01-02T03:04Z
 * - 2020-01-02Z
 * - 2020-01-02+01:30
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeAfterOrTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is after the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeAfter(
    expected: ChronoZonedDateTime<*>
): Expect<T> = _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime] given as [String].
 *
 * The format is composed of {DateTime}{ZoneDesignator}.
 *
 * DateTime:
 * - yyyy-mm-ddThh:mm:ss.SSS (up to 9 digits for nanoseconds)
 * - yyyy-mm-ddThh:mm:ss
 * - yyyy-mm-ddThh:mm
 * - yyyy-mm-dd
 *
 * And for ZoneDesignator:
 * - Z
 * - +hh
 * - +hh:mm
 * - -hh
 * - -hh:mm
 *
 * Here are some examples on how it can look combined
 * - 2020-01-02T03:04:05Z
 * - 2020-01-02T03:04Z
 * - 2020-01-02Z
 * - 2020-01-02+01:30
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeAfter(
    expected: String
): Expect<T> = _logicAppend { isAfter(expected) }
