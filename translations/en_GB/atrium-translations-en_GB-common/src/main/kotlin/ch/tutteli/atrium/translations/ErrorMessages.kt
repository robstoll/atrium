package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    DEDSCRIPTION_BASED_ON_SUBJECT("CANNOT show description as it is based on subject which is not defined"),
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("CANNOT evaluate representation as it is based on subject which is not defined."),

    @Deprecated(
        "Is no longer used, use ErrorMessages of atrium-core-api; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.creating.ErrorMessages.AT_LEAST_ONE_ASSERTION_DEFINED")
    )
    AT_LEAST_ONE_ASSERTION_DEFINED("at least one assertion defined"),

    @Deprecated(
        "Is no longer used, use ErrorMessages of atrium-core-api; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.creating.ErrorMessages.FORGOT_DO_DEFINE_ASSERTION")
    )
    FORGOT_DO_DEFINE_ASSERTION("You forgot to define assertions in the assertionCreator-lambda"),

    @Deprecated(
        "Is no longer used, use ErrorMessages of atrium-core-api; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.creating.ErrorMessages.HINT_AT_LEAST_ONE_ASSERTION_DEFINED")
    )
    HINT_AT_LEAST_ONE_ASSERTION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),

    @Deprecated("Will be removed with 1.0.0")
    SUBJECT_ACCESSED_TOO_EARLY("Could not evaluate sub-assertions; the subject was accessed too early. Please report a bug at $BUG_REPORT_URL including stacktrace if possible -- thank you"),
}
