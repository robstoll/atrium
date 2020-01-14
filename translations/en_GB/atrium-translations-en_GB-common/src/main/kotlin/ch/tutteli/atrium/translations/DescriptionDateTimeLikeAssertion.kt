package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
enum class DescriptionDateTimeLikeAssertion(override val value: String) : StringBasedTranslatable {
    YEAR("year"),
    MONTH("month"),
    DAY_OF_WEEK("day of week"),
    IS_AFTER_OR_EQUAL("is after or equal"),
    IS_AFTER("is after"),
    IS_BEFORE_OR_EQUAL("is before or equal"),
    IS_BEFORE("is before"),
    IS_EQUAL_TO("is equal to"),
    DAY("day"),
    SAME_DAY("is the same day as")
}
