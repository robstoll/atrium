package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceAssertion(override val value: String) : StringBasedTranslatable {
    AT_LEAST("ist zumindest"),
    AT_MOST("ist höchstens"),
    BLANK("blank"),
    CONTAINS("enthält"),
    CONTAINS_NOT("enthält nicht"),
    EMPTY("empty"),
    ENDS_WITH("endet mit"),
    ENDS_NOT_WITH("endet nicht mit"),
    EXACTLY("ist genau"),
    IGNORING_CASE("%s, Gross-/Kleinschreibung ignorierend"),
    MISMATCHES("stimmt nicht vollständig überein mit"),
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),
    STARTS_WITH("beginnt mit"),
    STARTS_NOT_WITH("beginnt nicht mit"),
    STRING_MATCHING_REGEX("Zeichenkette übereinstimmend mit Regex"),
    VALUE("Wert"),
}
