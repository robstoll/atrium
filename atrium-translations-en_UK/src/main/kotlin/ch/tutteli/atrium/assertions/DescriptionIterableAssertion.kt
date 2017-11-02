package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionIterableAssertion(override val value: String) : ISimpleTranslatable {
    AN_ENTRY_WHICH("an entry which"),
    AT_LEAST("is at least"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    AN_ENTRY_WHICH_IS("an entry which is"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
    WARNING_ADDITIONAL_ENTRIES("additional entries"),
    WARNING_MISMATCHES("following entries where mismatched"),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES("mismatches and additional entries detected"),
}
