// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
enum class DescriptionDateTimeLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    YEAR("year"),

    /** @since 0.18.0 */
    MONTH("month"),

    /** @since 0.18.0 */
    DAY_OF_WEEK("day of week"),

    /** @since 0.18.0 */
    TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS("to be after or the same point in time as"),

    /** @since 0.18.0 */
    TO_BE_AFTER("to be after"),

    /** @since 0.18.0 */
    TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS("to be before or the same point in time as"),

    /** @since 0.18.0 */
    TO_BE_BEFORE("to be before"),

    /** @since 0.18.0 */
    TO_BE_THE_SAME_POINT_IN_TIME_AS("to be the same point in time as"),

    /** @since 0.18.0 */
    DAY("day"),

    /** @since 0.18.0 */
    TO_BE_THE_SAME_DAY_AS("to be the same day as")
}
