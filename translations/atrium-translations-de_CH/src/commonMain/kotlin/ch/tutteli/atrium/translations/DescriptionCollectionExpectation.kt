package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [Collection].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionCollectionExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    EMPTY("leer"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    SIZE("Grösse"),
}
