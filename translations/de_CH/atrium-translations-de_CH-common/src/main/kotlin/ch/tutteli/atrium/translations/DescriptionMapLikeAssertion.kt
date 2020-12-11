package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapLikeAssertion(override val value: String) : StringBasedTranslatable {
    CONTAINS("enthält"),
    CONTAINS_KEY("enthält Key"),
    CONTAINS_NOT_KEY("enthält nicht den Key"),
    ENTRY_WITH_KEY("Eintrag %s"),
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),
    IN_ORDER("%, in gegebener Reihenfolge"),
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht"),
    SIZE("Grösse"),
    WARNING_ADDITIONAL_ENTRIES("zusätzliche Einträge entdeckt"),
}
