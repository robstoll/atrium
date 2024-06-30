// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionResultExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    IS_NOT_SUCCESS("❗❗ is not a Success"),

    /** @since 0.18.0 */
    VALUE("value"),

    /** @since 0.18.0 */
    IS_NOT_FAILURE("❗❗ is not failure"),

    /** @since 0.18.0 */
    EXCEPTION("exception")
}
