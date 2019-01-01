package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapAssertion(override val value: String) : StringBasedTranslatable {
    CANNOT_EVALUATE_KEY_DOES_NOT_EXIST("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- der gegebene Key existiert nicht.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    KEY_EXISTS("Key existiert"),
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht")
}
