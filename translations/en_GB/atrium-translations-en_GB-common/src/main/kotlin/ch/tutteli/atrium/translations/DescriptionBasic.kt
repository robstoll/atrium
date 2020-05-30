package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use a different wording in
 * a two different assertion functions.
 */
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    TO("to"),
    NOT_TO("not to"),

    @Deprecated(
        "Use DescriptionAnyAssertion.TO_BE or IS instead; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE")
    )
    TO_BE("equals"),

    @Deprecated(
        "Use DescriptionAnyAssertion.NOT_TO_BE or IS_NOT instead; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE")
    )
    NOT_TO_BE("does not equal"),

    IS("is"),
    IS_NOT("is not"),
    HAS("has"),
    HAS_NOT("has not"),
    WAS("was"),
    NONE("none")
}
