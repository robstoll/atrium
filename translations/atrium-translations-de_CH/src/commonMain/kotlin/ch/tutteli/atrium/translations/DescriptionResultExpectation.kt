package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionResultExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IS_NOT_SUCCESS("❗❗ ist kein Success"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    VALUE("Wert"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IS_NOT_FAILURE("❗❗ ist kein Fehler"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    EXCEPTION("Exception")
}
