package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE("ist"),
    NOT_TO_BE("ist nicht"),
    IS_SAME("ist dieselbe Instanz wie"),
    IS_NOT_SAME("ist nicht dieselbe Instanz wie"),
}
