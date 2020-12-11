package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapLikeAssertion(override val value: String) : StringBasedTranslatable {
    CONTAINS("contains"),
    CONTAINS_KEY("contains key"),
    CONTAINS_NOT_KEY("does not contain key"),
    ENTRY_WITH_KEY("entry %s"),
    IN_ANY_ORDER("%s, in any order"),
    IN_ANY_ORDER_ONLY("%s only, in any order"),
    IN_ORDER("%, in order"),
    IN_ORDER_ONLY("%s only, in order"),
    KEY_DOES_NOT_EXIST("❗❗ key does not exist"),
    SIZE("size"),
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
}
