package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    /** @since 0.18.0 */
    AT_LEAST_ONE_EXPECTATION_DEFINED("at least one expectation defined"),

    /** @since 0.18.0 */
    FORGOT_DO_DEFINE_EXPECTATION("You forgot to define expectations in the assertionCreator-lambda"),

    /** @since 0.18.0 */
    HINT_AT_LEAST_ONE_EXPECTATION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
}
