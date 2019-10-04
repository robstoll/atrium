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
    IS("ist"),
    IS_NOT("ist nicht"),
    HAS("hat")
}
