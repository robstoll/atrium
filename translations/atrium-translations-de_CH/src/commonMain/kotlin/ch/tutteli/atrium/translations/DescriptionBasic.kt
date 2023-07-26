package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use
 * a different wording in two different expectation functions.
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    TO("zu"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    NOT_TO("nicht zu"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    TO_BE("ist"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    NOT_TO_BE("ist nicht"),

    /** @since 0.18.0 */
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    TO_HAVE("hat"),

    /** @since 0.18.0 */
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    NOT_TO_HAVE("hat nicht"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    WAS("war"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    NONE("kein"),
}
