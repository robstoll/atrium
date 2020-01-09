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
<<<<<<< HEAD
    IS_AFTER_OR_EQUALS("is after or equals"),
    IS_AFTER("is after"),
    IS_BEFORE_OR_EQUALS("is before or equals"),
    IS_BEFORE("is before"),
    DAY("day")
=======
    DAY("day"),
    IS_AFTER_OR_EQUALS("is after or equals"),
>>>>>>> rename mentions of equal to equals
}
