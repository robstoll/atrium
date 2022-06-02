package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [Collection].
 */
enum class DescriptionCollectionExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    EMPTY("leer"),

    /** @since 0.18.0 */
    SIZE("Grösse"),
}
