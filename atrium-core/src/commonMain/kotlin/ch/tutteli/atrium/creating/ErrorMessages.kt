//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
@Deprecated(
    "switch to ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages")
)
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    @Deprecated(
        "Will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED")
    )
    /** @since 0.18.0 */
    AT_LEAST_ONE_EXPECTATION_DEFINED("at least one expectation defined"),

    @Deprecated(
        "Will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION")
    )
    /** @since 0.18.0 */
    FORGOT_DO_DEFINE_EXPECTATION("You forgot to define expectations in the assertionCreator-lambda"),

    @Deprecated(
        "Will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED")
    )
    /** @since 0.18.0 */
    HINT_AT_LEAST_ONE_EXPECTATION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
}
