package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    ALL("all entries"),
    AN_ENTRY_WHICH("an entry which"),
    AN_ENTRY_WHICH_IS("an entry which is"),
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    ENTRY_WITH_INDEX("entry %s"),
    EXACTLY("is exactly"),
    HAS_ELEMENT("has at least one element"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    IN_ORDER("%, in order"),
    IN_ORDER_ONLY("%s only, in order"),
    IN_ORDER_ONLY_GROUPED("%s only, in order, grouped"),
    INDEX("index %s"),
    INDEX_FROM_TO("index %s..%s"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` has no next entry.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` returns only `null` for `next()`.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
    WARNING_MISMATCHES("following entries were mismatched"),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES("mismatches and additional entries detected"),
}

internal const val COULD_NOT_EVALUATE_DEFINED_ASSERTIONS = "Could not evaluate the defined assertion(s)"
internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS = "Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions"
