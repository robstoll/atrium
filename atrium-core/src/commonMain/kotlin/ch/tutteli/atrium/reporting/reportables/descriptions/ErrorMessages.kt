package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.TextElement


/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val string: String) : TextElement {

    /** @since 1.2.0 (but existed in ch.tutteli.atrium.creating.ErrorMessages since @since 0.18.0) */
    AT_LEAST_ONE_EXPECTATION_DEFINED("at least one expectation defined"),

    /** @since 1.2.0 (but existed in ch.tutteli.atrium.creating.ErrorMessages since @since 0.18.0) */
    FORGOT_DO_DEFINE_EXPECTATION("You forgot to create expectations in the expectationCreator-lambda"),

    /** @since 1.2.0 */
    DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED("Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`"),
}
