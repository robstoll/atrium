//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionResultAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionResultExpectation.IS_NOT_SUCCESS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionResultExpectation.IS_NOT_SUCCESS")
    )
    IS_NOT_SUCCESS("❗❗ ist kein Success"),

    @Deprecated(
        "Use DescriptionResultExpectation.VALUE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionResultExpectation.VALUE")
    )
    VALUE("Wert"),

    @Deprecated(
        "Use DescriptionResultExpectation.IS_NOT_FAILURE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionResultExpectation.IS_NOT_FAILURE")
    )
    IS_NOT_FAILURE("❗❗ ist kein Fehler"),

    @Deprecated(
        "Use DescriptionResultExpectation.EXCEPTION; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionResultExpectation.EXCEPTION")
    )
    EXCEPTION("Exception")
}
