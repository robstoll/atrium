package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionIterableAssertion(override val value: String) : ISimpleTranslatable {
    AN_ENTRY_WHICH("an entry which:"),
    AT_LEAST("is at least"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    IN_ANY_ORDER("%s, in any order"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
}
