package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to date time like types such
 * as `ZonedDateTime`/`LocalDateTime` and co. on the JVM platform.
 */
enum class DescriptionDateTimeLikeProof(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    YEAR("year"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    MONTH("month"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    DAY_OF_WEEK("day of week"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS("to be after or the same point in time as"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_AFTER("to be after"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS("to be before or the same point in time as"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_BEFORE("to be before"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_THE_SAME_POINT_IN_TIME_AS("to be the same point in time as"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    DAY("day"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionDateTimeLikeExpectation)*/
    TO_BE_THE_SAME_DAY_AS("to be the same day as")
}
