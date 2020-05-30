@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionCollectionAssertion")
)
enum class DescriptionCollectionAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY(ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY.value),
    HAS_SIZE("hat die Grösse"),
}
