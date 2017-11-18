package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionIterableAssertion(override val value: String) : ISimpleTranslatable {
    AN_ENTRY_WHICH("an entry which"),
    AN_ENTRY_WHICH_IS("an entry which is"),
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    EXACTLY("is exactly"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
    WARNING_SUBJECT_NOT_SET("Could not evaluate the defined assertion(s) -- `Iterable` has no next entry.\n" +
        "It is not possible to evaluate the defined assertions because at least one of them requires to access an entry\n" +
        "whereas the given `Iterable` returned `false` for `hasNext()`.\n" +
        "In case you have not used `subject` in your assertion and your assertion does not access `subject` through reflection,\n" +
        "then most probably one of the used assertion functions is not implemented properly.\n" +
        "Please report a bug to the creator of the assertion function and pass on the hint, that assertion functions can be tested against `SubjectLessAssertionSpec` (located in atrium-spec).\n" +
        "Thank you :)"),
    WARNING_ADDITIONAL_ENTRIES("additional entries"),
    WARNING_MISMATCHES("following entries where mismatched"),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES("mismatches and additional entries detected"),
}
