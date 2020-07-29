package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE("ist"),
    NOT_TO_BE("ist nicht"),
    IS_A("ist eine Instanz vom Typ"),
    IS_SAME("ist dieselbe Instanz wie"),
    IS_NOT_SAME("ist nicht dieselbe Instanz wie"),
    IS_NONE_OF("ist nicht")
}
