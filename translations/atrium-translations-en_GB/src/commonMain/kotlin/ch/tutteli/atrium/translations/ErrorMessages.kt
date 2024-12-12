// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
@Deprecated(
    "Switch to ErrorMessages from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.ErrorMessages")
)
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.ErrorMessages.DESCRIPTION_BASED_ON_SUBJECT")
    )
    DESCRIPTION_BASED_ON_SUBJECT("CANNOT show description as it is based on subject which is not defined"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED")
    )
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("CANNOT evaluate representation as it is based on subject which is not defined."),
}
