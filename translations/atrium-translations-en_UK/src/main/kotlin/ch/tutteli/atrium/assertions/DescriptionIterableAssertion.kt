package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    AN_ENTRY_WHICH("an entry which"),
    AN_ENTRY_WHICH_IS("an entry which is"),
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    ENTRY_WITH_INDEX("entry %s"),
    EXACTLY("is exactly"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    IN_ORDER("%, in order"),
    IN_ORDER_ONLY("%s only, in order"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("Could not evaluate the defined assertion(s) -- `Iterable` has no next entry.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("Could not evaluate the defined assertion(s) -- `Iterable` returns only `null` for `next()`.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
    WARNING_MISMATCHES("following entries were mismatched"),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES("mismatches and additional entries detected"),
}

internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS = "Visit the following site for an explanation: https://robstoll.github.io/atrium/could-not-evaluate-assertions"
