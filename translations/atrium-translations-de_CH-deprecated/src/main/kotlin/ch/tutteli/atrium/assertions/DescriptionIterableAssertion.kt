@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated(
    "use the description from package translations, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionIterableAssertion")
)
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    AN_ENTRY_WHICH(ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH.value),
    AN_ENTRY_WHICH_IS(ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.value),
    AT_LEAST(ch.tutteli.atrium.translations.DescriptionIterableAssertion.AT_LEAST.value),
    AT_MOST(ch.tutteli.atrium.translations.DescriptionIterableAssertion.AT_MOST.value),
    CONTAINS(ch.tutteli.atrium.translations.DescriptionIterableAssertion.CONTAINS.value),
    CONTAINS_NOT(ch.tutteli.atrium.translations.DescriptionIterableAssertion.CONTAINS_NOT.value),
    ENTRY_WITH_INDEX(ch.tutteli.atrium.translations.DescriptionIterableAssertion.ENTRY_WITH_INDEX.value),
    EXACTLY(ch.tutteli.atrium.translations.DescriptionIterableAssertion.EXACTLY.value),
    IN_ANY_ORDER(ch.tutteli.atrium.translations.DescriptionIterableAssertion.IN_ANY_ORDER.value),
    IN_ANY_ORDER_ONLY(ch.tutteli.atrium.translations.DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.value),
    IN_ORDER(ch.tutteli.atrium.translations.DescriptionIterableAssertion.IN_ORDER.value),
    IN_ORDER_ONLY(ch.tutteli.atrium.translations.DescriptionIterableAssertion.IN_ORDER_ONLY.value),
    NUMBER_OF_OCCURRENCES(ch.tutteli.atrium.translations.DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.value),
    SIZE_EXCEEDED(ch.tutteli.atrium.translations.DescriptionIterableAssertion.SIZE_EXCEEDED.value),
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE(ch.tutteli.atrium.translations.DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE.value),
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL(ch.tutteli.atrium.translations.DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL.value),
    WARNING_ADDITIONAL_ENTRIES(ch.tutteli.atrium.translations.DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.value),
    WARNING_MISMATCHES(ch.tutteli.atrium.translations.DescriptionIterableAssertion.WARNING_MISMATCHES.value),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES(ch.tutteli.atrium.translations.DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES.value),
}
