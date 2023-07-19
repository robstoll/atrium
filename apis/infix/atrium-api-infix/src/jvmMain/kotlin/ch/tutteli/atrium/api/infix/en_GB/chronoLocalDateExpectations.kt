package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.chrono.ChronoLocalDate

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeBeforeChronoLocalDate
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeBefore(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBefore(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [IllegalArgumentException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeBeforeString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeBefore(expected: String): Expect<T> =
    _logicAppend { isBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeBeforeOrTheSamePointInTimeAsChronoLocalDate
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is before or equal the [expected] [java.time.LocalDate] given as [String].
 * The [expected] parameter needs to be in the form of **yyyy-mm-dd** or else an exception will be thrown.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [IllegalArgumentException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeBeforeOrTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeBeforeOrTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isBeforeOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeTheSamePointInTimeAsChronoLocalDate
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is equal to the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [IllegalArgumentException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeAfterOrTheSamePointInTimeAsChronoLocalDate
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeAfterOrTheSamePointInTimeAs(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after or equal the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [IllegalArgumentException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeAfterOrTheSamePointInTimeAsString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeAfterOrTheSamePointInTimeAs(expected: String): Expect<T> =
    _logicAppend { isAfterOrEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [ChronoLocalDate].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeAfterChronoLocalDate
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeAfter(expected: ChronoLocalDate): Expect<T> =
    _logicAppend { isAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [ChronoLocalDate])
 * is after the [expected] [java.time.LocalDate] given as [String].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws [IllegalArgumentException] in case [expected] is not in the form of **yyyy-mm-dd**
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoLocalDateExpectationSamples.toBeAfterString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.toBeAfter(expected: String): Expect<T> =
    _logicAppend { isAfter(expected) }

