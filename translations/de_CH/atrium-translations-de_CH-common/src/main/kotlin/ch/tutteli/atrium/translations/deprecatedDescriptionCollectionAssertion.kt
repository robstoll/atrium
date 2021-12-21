//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionCollectionAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionCollectionExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCollectionExpectation.EMPTY")
    )
    EMPTY("leer"),

    @Deprecated(
        "Use DescriptionCollectionExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCollectionExpectation.SIZE")
    )
    SIZE("Grösse"),
}
