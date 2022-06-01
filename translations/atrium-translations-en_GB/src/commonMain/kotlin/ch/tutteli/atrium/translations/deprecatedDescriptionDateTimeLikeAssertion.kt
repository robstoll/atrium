//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionDateTimeLikeAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.YEAR")
    )
    YEAR("year"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.MONTH")
    )
    MONTH("month"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.DAY_OF_WEEK")
    )
    DAY_OF_WEEK("day of week"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS")
    )
    IS_AFTER_OR_EQUAL("is after or equal"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_AFTER")
    )
    IS_AFTER("is after"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS")
    )
    IS_BEFORE_OR_EQUAL("is before or equal"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_BEFORE")
    )
    IS_BEFORE("is before"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_POINT_IN_TIME_AS")
    )
    IS_EQUAL_TO("is equal to"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.DAY")
    )
    DAY("day"),

    @Deprecated(
        "Use DescriptionDateTimeLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_DAY_AS")
    )
    SAME_DAY("is the same day as")
}
