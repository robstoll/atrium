package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("is instance of type"),
    IS_SAME("is the same as"),
    IS_NOT_SAME("is not the same as"),
}
