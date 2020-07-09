@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionComparableAssertion")
)
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN(ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_LESS_THAN.value),
    IS_LESS_OR_EQUALS(ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUAL.value),
    IS_GREATER_THAN(ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_GREATER_THAN.value),
    IS_GREATER_OR_EQUALS(ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUAL.value),
}
