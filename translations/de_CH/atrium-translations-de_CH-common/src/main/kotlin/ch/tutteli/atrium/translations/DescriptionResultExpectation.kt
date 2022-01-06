package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionResultExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    IS_NOT_SUCCESS("❗❗ ist kein Success"),

    /** @since 0.18.0 */
    VALUE("Wert"),

    /** @since 0.18.0 */
    IS_NOT_FAILURE("❗❗ ist kein Fehler"),

    /** @since 0.18.0 */
    EXCEPTION("Exception")
}
