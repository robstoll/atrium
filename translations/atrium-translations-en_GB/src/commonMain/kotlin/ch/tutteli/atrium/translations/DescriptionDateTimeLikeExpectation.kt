// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
@Deprecated(
    "Switch to DescriptionDateTimeLikeProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof")
)
enum class DescriptionDateTimeLikeExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.YEAR")
    )
    /** @since 0.18.0 */
    YEAR("year"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.MONTH")
    )
    /** @since 0.18.0 */
    MONTH("month"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.DAY_OF_WEEK")
    )
    /** @since 0.18.0 */
    DAY_OF_WEEK("day of week"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS")
    )
    /** @since 0.18.0 */
    TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS("to be after or the same point in time as"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_AFTER")
    )
    /** @since 0.18.0 */
    TO_BE_AFTER("to be after"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS")
    )
    /** @since 0.18.0 */
    TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS("to be before or the same point in time as"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_BEFORE")
    )
    /** @since 0.18.0 */
    TO_BE_BEFORE("to be before"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_THE_SAME_POINT_IN_TIME_AS")
    )
    /** @since 0.18.0 */
    TO_BE_THE_SAME_POINT_IN_TIME_AS("to be the same point in time as"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.DAY")
    )
    /** @since 0.18.0 */
    DAY("day"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.TO_BE_THE_SAME_DAY_AS")
    )
    /** @since 0.18.0 */
    TO_BE_THE_SAME_DAY_AS("to be the same day as")
}
