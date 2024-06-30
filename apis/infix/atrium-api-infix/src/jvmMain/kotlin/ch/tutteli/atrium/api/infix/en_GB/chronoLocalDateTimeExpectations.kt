//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [ChronoLocalDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeBeforeChronoLocalDateTime
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeBefore(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a
 * date without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 *  @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeBeforeString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeBefore(
    expected: String
): Expect<T> = _logicAppend { isBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before or equal the [expected] [ChronoLocalDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeBeforeOrTheSamePointInTimeAsChronoLocalDateTime
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a
 * date without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isBeforeOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is equal to the [expected] [ChronoLocalDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeTheSamePointInTimeAsChronoLocalDateTime
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeTheSamePointInTimeAs(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is after or equal the [expected] [ChronoLocalDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsChronoLocalDateTime
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeAfterOrTheSamePointInTimeAs(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeAfterOrTheSamePointInTimeAs(
    expected: String
): Expect<T> = _logicAppend { isAfterOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is after the [expected] [ChronoLocalDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeAfterChronoLocalDateTime
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeAfter(
    expected: ChronoLocalDateTime<*>
): Expect<T> = _logicAppend { isAfter(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDateTime])
 * is before the [expected] [LocalDateTime] given as [String] in (modified) ISO 8601 format.
 * The string will be converted to a LocalDateTime according to ISO 8601 but with a slight deviation.
 * The alternative notation (e.g. 20200401120001 instead of 2020-04-01T12:00:01) is not supported and we accept a date
 * without time in which case 00:00:00 is used. Following the supported formats from the longest to the shortest:
 * yyyy-mm-ddThh:mm:ss.sss (up to 9 digits for nanoseconds)
 * yyyy-mm-ddThh:mm:ss
 * yyyy-mm-ddThh:mm
 * yyyy-mm-dd
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [java.time.DateTimeException] in case an unsupported format is given.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateTimeExpectationSamples.toBeAfterString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.toBeAfter(
    expected: String
): Expect<T> = _logicAppend { isAfter(expected) }
