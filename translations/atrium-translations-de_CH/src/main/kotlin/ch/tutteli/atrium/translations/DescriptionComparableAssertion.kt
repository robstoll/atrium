package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("ist weniger als"),
    IS_LESS_OR_EQUALS("ist weniger oder gleich"),
    IS_GREATER_THAN("ist grösser als"),
    IS_GREATER_OR_EQUALS("ist grösser oder gleich"),
}
