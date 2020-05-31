package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("is less than"),
    IS_LESS_OR_EQUALS("is less or equals"),
    IS_GREATER_THAN("is greater than"),
    IS_GREATER_OR_EQUALS("is greater or equals"),
    IS_EQUAL("is equal comparing to"),
}
