//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ALL_ELEMENTS")
    )
    ALL("all elements"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS")
    )
    AN_ELEMENT_WHICH("an element which"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS")
    )
    AN_ELEMENT_WHICH_EQUALS("an element which equals"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS")
    )
    AN_ENTRY_WHICH(AN_ELEMENT_WHICH.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS")
    )
    AN_ENTRY_WHICH_IS(AN_ELEMENT_WHICH_EQUALS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AT_LEAST")
    )
    AT_LEAST("is at least"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AT_MOST")
    )
    AT_MOST("is at most"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.TO_CONTAIN")
    )
    CONTAINS("contains"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NOT_TO_CONTAIN")
    )
    CONTAINS_NOT("does not contain"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX")
    )
    ELEMENT_WITH_INDEX("element %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX")
    )
    ENTRY_WITH_INDEX(ELEMENT_WITH_INDEX.getDefault()),

    //TODO remove with 0.19.0
    @Deprecated("Will be removed with 0.19.0")
    HAS_ELEMENT("has at least one element"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.EXACTLY")
    )
    EXACTLY("is exactly"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ANY_ORDER")
    )
    IN_ANY_ORDER("%s, in any order"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ANY_ORDER_ONLY")
    )
    IN_ANY_ORDER_ONLY("%s only, in any order"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER")
    )
    IN_ORDER("%, in order"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER_ONLY")
    )
    IN_ORDER_ONLY("%s only, in order"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER_ONLY_GROUPED")
    )
    IN_ORDER_ONLY_GROUPED("%s only, in order, grouped"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.INDEX")
    )
    INDEX("index %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.INDEX_FROM_TO")
    )
    INDEX_FROM_TO("index %s..%s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NUMBER_OF_SUCH_ELEMENTS")
    )
    NUMBER_OF_OCCURRENCES("number of such elements"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.SIZE_EXCEEDED")
    )
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),

    @Deprecated("Will be removed with 0.19.0")
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` has no next entry.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),

    @Deprecated("Will be removed with 0.19.0")
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` returns only `null` for `next()`.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_ADDITIONAL_ELEMENTS")
    )
    WARNING_ADDITIONAL_ELEMENTS("additional elements detected"),

    @Deprecated(
        "Use WARNING_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest",
        ReplaceWith("WARNING_ADDITIONAL_ELEMENTS")
    )
    WARNING_ADDITIONAL_ENTRIES(WARNING_ADDITIONAL_ELEMENTS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES")
    )
    WARNING_MISMATCHES("following elements were mismatched"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS")
    )
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("mismatches and additional elements detected"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS")
    )
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES(WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.A_NEXT_ELEMENT")
    )
    NEXT_ELEMENT("a next element"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NO_ELEMENTS")
    )
    NO_ELEMENTS("❗❗ cannot be determined, empty Iterable"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.DUPLICATE_ELEMENTS")
    )
    DUPLICATE_ELEMENTS("duplicate elements"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.DUPLICATED_BY")
    )
    DUPLICATED_BY("duplicated by index: %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND")
    )
    ELEMENT_NOT_FOUND("but no such element was found"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NUMBER_OF_ELEMENTS_FOUND")
    )
    NUMBER_OF_ELEMENTS_FOUND("and %s such elements were found")
}

//TODO remove with 0.19.0
@Deprecated("Will be removed with 0.19.0 without replacement")
internal const val COULD_NOT_EVALUATE_DEFINED_ASSERTIONS = "Could not evaluate the defined assertion(s)"

@Deprecated("Will be removed with 0.19.0 without replacement")
internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS =
    "Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions"
