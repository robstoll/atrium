package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    AT_LEAST_ONE_ASSERTION_DEFINED("at least one assertion defined"),
    FORGOT_DO_DEFINE_ASSERTION("You forgot to define assertions in the assertionCreator-lambda"),
    HINT_AT_LEAST_ONE_ASSERTION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
    @Deprecated("Will be removed with 0.16.0")
    SUBJECT_ACCESSED_TOO_EARLY("Could not evaluate sub-assertions; the subject was accessed too early. Please report a bug at $BUG_REPORT_URL including stacktrace if possible -- thank you"),
}
