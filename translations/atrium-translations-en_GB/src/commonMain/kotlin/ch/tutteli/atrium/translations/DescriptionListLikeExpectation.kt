package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionListLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    INDEX_OUT_OF_BOUNDS("❗❗ Index out of bounds"),

    /** @since 1.2.0 */
    IS_EMPTY("❗❗List is empty"),
}
