@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion")
)
enum class DescriptionCharSequenceAssertion(override val value: String) : StringBasedTranslatable {
    AT_LEAST(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_LEAST.value),
    AT_MOST(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST.value),
    CONTAINS(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS.value),
    CONTAINS_NOT(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS_NOT.value),
    EMPTY(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EMPTY.value),
    ENDS_WITH(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_WITH.value),
    ENDS_NOT_WITH(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_NOT_WITH.value),
    EXACTLY(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EXACTLY.value),
    IGNORING_CASE(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.IGNORING_CASE.value),
    NUMBER_OF_OCCURRENCES(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.value),
    STARTS_WITH(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.STARTS_WITH.value),
    STARTS_NOT_WITH(ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.STARTS_NOT_WITH.value),
}
