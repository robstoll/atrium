package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use
 * a different wording in two different expectation functions.
 */
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    TO("zu"),
    NOT_TO("nicht zu"),

    TO_BE("ist"),
    NOT_TO_BE("ist nicht"),

    /** @since 0.18.0 */
    TO_HAVE("hat"),

    /** @since 0.18.0 */
    NOT_TO_HAVE("hat nicht"),

    WAS("war"),
    NONE("kein"),
}
