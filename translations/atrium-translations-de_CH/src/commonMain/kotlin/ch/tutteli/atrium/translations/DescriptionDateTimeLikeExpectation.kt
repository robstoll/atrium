package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions
 * which are applicable to date like instances (e.g. LocalDate, LocaleDateTime, ZonedDateTime etc.)
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionDateTimeLikeExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    YEAR("Jahr"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    MONTH("Monat"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    DAY_OF_WEEK("Wochentag"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS("ist nach oder gleichzeitig"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_AFTER("ist nach"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS("ist vor oder gleichzeitig"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_BEFORE("ist vor"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_THE_SAME_POINT_IN_TIME_AS("ist gleichzeitig wie"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    DAY("Tag"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_THE_SAME_DAY_AS("ist derselbe Tag wie")
}
