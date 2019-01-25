package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapAssertion(override val value: String) : StringBasedTranslatable {
    CANNOT_EVALUATE_KEY_DOES_NOT_EXIST("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- der gegebene Key existiert nicht.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    CONTAINS_IN_ANY_ORDER("enthält, in beliebiger Reihenfolge"),
    CONTAINS_KEY("enthält Key"),
    CONTAINS_NOT_KEY("enthält nicht den Key"),
    ENTRY_WITH_KEY("Eintrag %s"),
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht"),
    MAP_CONTAINS_ONLY("map enthält Key, die nicht bestätigt wurden"), //TODO this is google translation please take a look
    MAP_KEYS_MISMATCH("Key nicht übereinstimmend")  //TODO this is google translation please take a look
}
