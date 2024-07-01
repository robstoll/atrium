// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [Collection].
 */
enum class DescriptionCollectionExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    EMPTY("empty"),

    /** @since 0.18.0 */
    SIZE("size"),
}
