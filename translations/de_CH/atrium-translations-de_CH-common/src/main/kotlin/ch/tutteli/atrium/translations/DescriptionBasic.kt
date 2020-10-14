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

    @Deprecated(
        "Use DescriptionAnyAssertion.TO_BE or IS instead; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE")
    )
    TO_BE("ist"),

    @Deprecated(
        "Use DescriptionAnyAssertion.NOT_TO_BE or IS_NOT instead; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE")
    )
    NOT_TO_BE("ist nicht"),

    IS("ist"),
    IS_NOT("ist nicht"),
    HAS("hat"),
    HAS_NOT("hat nicht"),
    WAS("war"),
    NONE("kein"),
    //TODO remove or update text
    CONTAINS_DUPLICATES("contains duplicates")
}
