package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    AT_LEAST_ONE_ASSERTION_DEFINED("at least one assertion defined"),
    //TODO 0.16.0 deprecate and replace with FORGOT_DO_DEFINE_EXPECTATION
    FORGOT_DO_DEFINE_ASSERTION("You forgot to define assertions in the assertionCreator-lambda"),
    HINT_AT_LEAST_ONE_ASSERTION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
}
