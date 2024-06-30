//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PleaseUseReplacementException
import ch.tutteli.atrium.logic.*
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * Expects that the subject of `this` expectation (a [ChronoZonedDateTime])
 * is before the [expected] [ChronoZonedDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeBeforeChronoZonedDateTime
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeBeforeString
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeBeforeOrTheSamePointInTimeAsChronoZonedDateTime
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeBeforeOrTheSamePointInTimeAsString
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeTheSamePointInTimeAsChronoZonedDateTime
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeTheSamePointInTimeAsString
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsChronoZonedDateTime
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsString
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeAfterChronoZonedDateTime
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ChronoZonedDateTimeExpectationSamples.toBeAfterString
 *
 * @since 0.17.0
 */
infix fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.toBeAfter(
    expected: String
): Expect<T> = _logicAppend { isAfter(expected) }

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeBefore] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'toBeLessThan' is deprecated in favour of 'toBeBefore' which is based on ChronoZonedDateTime.isBefore instead of compareTo",
    ReplaceWith("toBeBefore(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.toBeLessThan(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'toBeLessThan' is deprecated in favour of 'toBeBefore', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeBeforeOrTheSamePointInTimeAs] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'toBeLessThanOrEqualTo is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs' which is based on ChronoZonedDateTime.isBefore and ChronoZonedDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeBeforeOrTheSamePointInTimeAs(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.toBeLessThanOrEqualTo(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'toBeLessThanOrEqualTo is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeBeforeOrTheSamePointInTimeAs] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'notToBeGreaterThan' is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs' which is based on ChronoZonedDateTime.isBefore and ChronoZonedDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeBeforeOrTheSamePointInTimeAs(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.notToBeGreaterThan(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'notToBeGreaterThan' is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeTheSamePointInTimeAs] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'toBeEqualComparingTo' is deprecated in favour of 'toBeTheSamePointInTimeAs' which is based on ChronoZonedDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeTheSamePointInTimeAs(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.toBeEqualComparingTo(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'toBeEqualComparingTo' is deprecated in favour of 'toBeTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeAfterOrTheSamePointInTimeAs] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'toBeGreaterThanOrEqualTo' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs' which is based on ChronoZonedDateTime.isAfter and ChronoZonedDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeAfterOrTheSamePointInTimeAs(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.toBeGreaterThanOrEqualTo(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'toBeGreaterThanOrEqualTo' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeAfterOrTheSamePointInTimeAs] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'notToBeLessThan' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs' which is based on ChronoZonedDateTime.isAfter and ChronoZonedDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeAfterOrTheSamePointInTimeAs(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.notToBeLessThan(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'notToBeLessThan' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall give a hint to the user/guide the user to use [toBeAfter] instead.
 *
 * @since 1.0.0
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "'toBeGreaterThan' is deprecated in favour of 'toBeAfter' which is based on ChronoZonedDateTime.isAfter instead of compareTo",
    ReplaceWith("toBeAfter(expected)")
)
infix fun <T : ChronoZonedDateTime<T>> Expect<T>.toBeGreaterThan(expected: T): Nothing =
    throw PleaseUseReplacementException(
        "'toBeGreaterThan' is deprecated in favour of 'toBeAfter', use a feature extractor if you want to base your expectation on compareTo"
    )


