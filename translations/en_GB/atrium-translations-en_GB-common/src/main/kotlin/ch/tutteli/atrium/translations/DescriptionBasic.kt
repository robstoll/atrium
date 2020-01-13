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
    TO_BE("is equal to"),
    NOT_TO_BE("is not equal to"),
    IS("is"),
    IS_NOT("is not"),
    HAS("has"),
    HAS_NOT("has not"),
    WAS("was"),
    NONE("none")
}
