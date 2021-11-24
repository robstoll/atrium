package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "USE AT_LEAST_ONE_EXPECTATION_DEFINED, will be removed with 0.19.0",
        ReplaceWith("AT_LEAST_ONE_EXPECTATION_DEFINED")
    )
    AT_LEAST_ONE_ASSERTION_DEFINED("at least one assertion defined"),

    /** @since 0.18.0 */
    AT_LEAST_ONE_EXPECTATION_DEFINED("at least one expectation defined"),

    @Deprecated(
        "USE FORGOT_DO_DEFINE_EXPECTATION, will be removed with 0.19.0",
        ReplaceWith("FORGOT_DO_DEFINE_EXPECTATION")
    )
    FORGOT_DO_DEFINE_ASSERTION("You forgot to define assertions in the assertionCreator-lambda"),

    /** @since 0.18.0 */
    FORGOT_DO_DEFINE_EXPECTATION("You forgot to define expectations in the expectationCreator-lambda"),

    @Deprecated(
        "USE HINT_AT_LEAST_ONE_EXPECTATION_DEFINED, will be removed with 0.19.0",
        ReplaceWith("HINT_AT_LEAST_ONE_EXPECTATION_DEFINED")
    )
    HINT_AT_LEAST_ONE_ASSERTION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),

    /** @since 0.18.0 */
    HINT_AT_LEAST_ONE_EXPECTATION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
}
