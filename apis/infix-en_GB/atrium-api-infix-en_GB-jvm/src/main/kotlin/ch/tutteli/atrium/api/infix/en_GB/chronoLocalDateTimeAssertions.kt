@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBefore(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before or equal the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBeforeOrEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is after the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfter(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is after or equal the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfterOrEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is equal to the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a
 * date without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBefore(
    expected: String
): Expect<T> = _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a
 * date without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBeforeOrEqual(
    expected: String
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfter(
    expected: String
): Expect<T> = _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfterOrEqual(
    expected: String
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isEqual(
    expected: String
): Expect<T> = _logicAppend { isEqual(expected) }
