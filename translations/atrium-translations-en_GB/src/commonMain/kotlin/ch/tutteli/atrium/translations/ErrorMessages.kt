package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    DESCRIPTION_BASED_ON_SUBJECT("CANNOT show description as it is based on subject which is not defined"),

    @Deprecated(
        "Use DESCRIPTION_BASED_ON_SUBJECT; will be removed with 1.0.0 at the latest",
        ReplaceWith("DESCRIPTION_BASED_ON_SUBJECT")
    )
    DEDSCRIPTION_BASED_ON_SUBJECT("CANNOT show description as it is based on subject which is not defined"),

    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("CANNOT evaluate representation as it is based on subject which is not defined."),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    SUBJECT_ACCESSED_TOO_EARLY("Could not evaluate sub-assertions; the subject was accessed too early. Please report a bug at $BUG_REPORT_URL including stacktrace if possible -- thank you"),
}
