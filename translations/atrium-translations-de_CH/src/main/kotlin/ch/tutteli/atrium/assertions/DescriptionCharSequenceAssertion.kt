package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceAssertion(override val value: String) : ISimpleTranslatable {
    AT_LEAST("ist zumindest"),
    AT_MOST("ist höchstens"),
    CONTAINS("enthält"),
    CONTAINS_NOT("enthält nicht"),
    EMPTY("empty"),
    ENDS_WITH("endet mit"),
    ENDS_NOT_WITH("endet nicht mit"),
    EXACTLY("ist genau"),
    IGNORING_CASE("%s, Gross-/Kleinschreibung ignoriert,"),
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),
    STARTS_WITH("beginnt mit"),
    STARTS_NOT_WITH("beginnt nicht mit"),
}
