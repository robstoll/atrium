package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
enum class DescriptionDateTimeLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    YEAR("Jahr"),

    /** @since 0.18.0 */
    MONTH("Monat"),

    /** @since 0.18.0 */
    DAY_OF_WEEK("Wochentag"),

    /** @since 0.18.0 */
    TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS("ist nach oder gleichzeitig"),

    /** @since 0.18.0 */
    TO_BE_AFTER("ist nach"),

    /** @since 0.18.0 */
    TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS("ist vor oder gleichzeitig"),

    /** @since 0.18.0 */
    TO_BE_BEFORE("ist vor"),

    /** @since 0.18.0 */
    TO_BE_THE_SAME_POINT_IN_TIME_AS("ist gleichzeitig wie"),

    /** @since 0.18.0 */
    DAY("Tag"),

    /** @since 0.18.0 */
    TO_BE_THE_SAME_DAY_AS("ist derselbe Tag wie")
}
