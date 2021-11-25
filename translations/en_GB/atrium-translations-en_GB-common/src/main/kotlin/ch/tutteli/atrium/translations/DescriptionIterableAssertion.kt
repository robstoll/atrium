package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    ALL("all entries"),
    AN_ELEMENT_WHICH("an element which"),
    AN_ELEMENT_WHICH_EQUALS("an element which equals"),
    @Deprecated("Use AN_ELEMENT_WHICH; will be removed with 1.0.0 at the latest", ReplaceWith("AN_ELEMENT_WHICH"))
    AN_ENTRY_WHICH(AN_ELEMENT_WHICH.getDefault()),
    @Deprecated("Use AN_ELEMENT_WHICH_EQUALS; will be removed with 1.0.0 at the latest", ReplaceWith("AN_ELEMENT_WHICH_EQUALS"))
    AN_ENTRY_WHICH_IS(AN_ELEMENT_WHICH_EQUALS.getDefault()),
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    ELEMENT_WITH_INDEX("element %s"),
    @Deprecated("Use ELEMENT_WITH_INDEX; will be removed with 1.0.0 at the latest", ReplaceWith("ELEMENT_WITH_INDEX"))
    ENTRY_WITH_INDEX(ELEMENT_WITH_INDEX.getDefault()),
    EXACTLY("is exactly"),
    HAS_ELEMENT("has at least one element"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    IN_ORDER("%, in order"),
    IN_ORDER_ONLY("%s only, in order"),
    IN_ORDER_ONLY_GROUPED("%s only, in order, grouped"),
    INDEX("index %s"),
    INDEX_FROM_TO("index %s..%s"),
    NUMBER_OF_OCCURRENCES("number of such entries"),
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` has no next entry.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` returns only `null` for `next()`.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    WARNING_ADDITIONAL_ELEMENTS("additional elements detected"),
    @Deprecated("Use WARNING_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest", ReplaceWith("WARNING_ADDITIONAL_ELEMENTS"))
    WARNING_ADDITIONAL_ENTRIES(WARNING_ADDITIONAL_ELEMENTS.getDefault()),
    WARNING_MISMATCHES("following elements were mismatched"),
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("mismatches and additional elements detected"),
    @Deprecated("Use WARNING_MISMATCHES_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest", ReplaceWith("WARNING_MISMATCHES_ADDITIONAL_ELEMENTS"))
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES(WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()),
    //TODO 0.18.0 replace with A_NEXT_ELEMENT
    NEXT_ELEMENT("a next element"),
    NO_ELEMENTS("❗❗ cannot be determined, empty Iterable"),
    DUPLICATE_ELEMENTS("duplicate elements"),
    DUPLICATED_BY("duplicated by index: %s"),
    ELEMENT_NOT_FOUND("but no such element was found"),
    NUMBER_OF_ELEMENTS_FOUND("and %s such elements were found")
}

internal const val COULD_NOT_EVALUATE_DEFINED_ASSERTIONS = "Could not evaluate the defined assertion(s)"
internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS =
    "Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions"
