// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use
 * a different wording in two different expectation functions.
 */
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    TO("to"),
    NOT_TO("not to"),

    TO_BE("to be"),
    NOT_TO_BE("not to be"),

    /** @since 0.18.0 */
    TO_HAVE("to have"),

    /** @since 0.18.0 */
    NOT_TO_HAVE("not to have"),

    WAS("was"),
    NONE("none"),
}
