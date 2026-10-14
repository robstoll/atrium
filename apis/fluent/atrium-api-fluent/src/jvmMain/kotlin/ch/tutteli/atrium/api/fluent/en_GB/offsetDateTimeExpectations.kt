package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PleaseUseReplacementException
import ch.tutteli.atrium.logic.*
import java.time.OffsetDateTime

/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime]) is before the [expected] [OffsetDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeBeforeOffsetDateTime
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeBefore(
    expected: OffsetDateTime
): Expect<OffsetDateTime> = _logicAppend { toBeBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is before the [expected] [OffsetDateTime] given as [String].
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeBeforeString
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeBefore(
    expected: String
): Expect<OffsetDateTime> = _logicAppend { toBeBefore(expected) }


/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is before or equals the [expected] [OffsetDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeBeforeOrTheSamePointInTimeAsOffsetDateTime
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeBeforeOrTheSamePointInTimeAs(
    expected: OffsetDateTime
): Expect<OffsetDateTime> = _logicAppend { this.toBeBeforeOrTheSamePointInTimeAs(expected) }


/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is before or equals the [expected] [OffsetDateTime] given as [String].
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeBeforeOrTheSamePointInTimeAsString
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeBeforeOrTheSamePointInTimeAs(
    expected: String
): Expect<OffsetDateTime> = _logicAppend { this.toBeBeforeOrTheSamePointInTimeAs(expected) }

/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is equal to the [expected] [OffsetDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeTheSamePointInTimeAsOffsetDateTime
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeTheSamePointInTimeAs(
    expected: OffsetDateTime
): Expect<OffsetDateTime> = _logicAppend { toBeEqual(expected) }

/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is equal to the [expected] [OffsetDateTime] given as [String].
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeTheSamePointInTimeAsString
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeTheSamePointInTimeAs(
    expected: String
): Expect<OffsetDateTime> = _logicAppend { toBeEqual(expected) }


/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is after or equal the [expected] [OffsetDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsOffsetDateTime
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeAfterOrTheSamePointInTimeAs(
    expected: OffsetDateTime
): Expect<OffsetDateTime> = _logicAppend { this.toBeAfterOrTheSamePointInTimeAs(expected) }

/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is after or equal the [expected] [OffsetDateTime] given as [String].
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeAfterOrTheSamePointInTimeAsString
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeAfterOrTheSamePointInTimeAs(
    expected: String
): Expect<OffsetDateTime> = _logicAppend { this.toBeAfterOrTheSamePointInTimeAs(expected) }


/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is after the [expected] [OffsetDateTime].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeAfterOffsetDateTime
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeAfter(
    expected: OffsetDateTime
): Expect<OffsetDateTime> = _logicAppend { toBeAfter(expected) }

/**
 * Expects that the subject of `this` expectation (a [OffsetDateTime])
 * is after the [expected] [OffsetDateTime] given as [String].
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.OffsetDateTimeExpectationSamples.toBeAfterString
 *
 * @since 1.3.0
 */
fun Expect<OffsetDateTime>.toBeAfter(
    expected: String
): Expect<OffsetDateTime> = _logicAppend { toBeAfter(expected) }

/**
 * Deprecated overload which shall help users to use [toBeBefore] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'toBeLessThan' is deprecated in favour of 'toBeBefore' which is based on OffsetDateTime.isBefore instead of compareTo",
    ReplaceWith("toBeBefore(expected)")
)
fun Expect<OffsetDateTime>.toBeLessThan(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'toBeLessThan' is deprecated in favour of 'toBeBefore', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeBeforeOrTheSamePointInTimeAs] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'toBeLessThanOrEqualTo' is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs' which is based on OffsetDateTime.isBefore and OffsetDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeBeforeOrTheSamePointInTimeAs(expected)")
)
fun Expect<OffsetDateTime>.toBeLessThanOrEqualTo(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'toBeLessThanOrEqualTo is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeBeforeOrTheSamePointInTimeAs] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'notToBeGreaterThan' is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs' which is based on OffsetDateTime.isBefore and OffsetDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeBeforeOrTheSamePointInTimeAs(expected)")
)
fun Expect<OffsetDateTime>.notToBeGreaterThan(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'notToBeGreaterThan' is deprecated in favour of 'toBeBeforeOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeTheSamePointInTimeAs] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'toBeEqualComparingTo' is deprecated in favour of 'toBeTheSamePointInTimeAs' which is based on OffsetDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeTheSamePointInTimeAs(expected)")
)
fun Expect<OffsetDateTime>.toBeEqualComparingTo(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'toBeEqualComparingTo' is deprecated in favour of 'toBeTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeAfterOrTheSamePointInTimeAs] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'toBeGreaterThanOrEqualTo' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs' which is based on OffsetDateTime.isAfter and OffsetDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeAfterOrTheSamePointInTimeAs(expected)")
)
fun Expect<OffsetDateTime>.toBeGreaterThanOrEqualTo(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'toBeGreaterThanOrEqualTo' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeAfterOrTheSamePointInTimeAs] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'notToBeLessThan' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs' which is based on OffsetDateTime.isAfter and OffsetDateTime.isEqual instead of compareTo",
    ReplaceWith("toBeAfterOrTheSamePointInTimeAs(expected)")
)
fun Expect<OffsetDateTime>.notToBeLessThan(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'notToBeLessThan' is deprecated in favour of 'toBeAfterOrTheSamePointInTimeAs', use a feature extractor if you want to base your expectation on compareTo"
    )

/**
 * Deprecated overload which shall help users to use [toBeAfter] instead.
 *
 * @since 1.3.0
 */
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
@Deprecated(
    "'toBeGreaterThan' is deprecated in favour of 'toBeAfter' which is based on OffsetDateTime.isAfter instead of compareTo",
    ReplaceWith("toBeAfter(expected)")
)
fun Expect<OffsetDateTime>.toBeGreaterThan(expected: OffsetDateTime): Nothing =
    throw PleaseUseReplacementException(
        "'toBeGreaterThan' is deprecated in favour of 'toBeAfter', use a feature extractor if you want to base your expectation on compareTo"
    )

