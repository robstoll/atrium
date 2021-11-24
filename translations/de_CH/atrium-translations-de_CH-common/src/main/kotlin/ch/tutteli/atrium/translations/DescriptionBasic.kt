package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use a different wording in
 * a two different assertion functions.
 */
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    TO("zu"),
    NOT_TO("nicht zu"),

    TO_BE("ist"),
    NOT_TO_BE("ist nicht"),

    HAS("hat"),
    HAS_NOT("hat nicht"),
    WAS("war"),
    NONE("kein"),

    @Deprecated("Use TO_BE instead; will be removed with 1.0.0 at the latest", ReplaceWith("TO_BE"))
    IS("ist"),

    @Deprecated("Use NOT_TO_BE instead; will be removed with 1.0.0 at the latest", ReplaceWith("NOT_TO_BE"))
    IS_NOT("ist nicht"),
}
