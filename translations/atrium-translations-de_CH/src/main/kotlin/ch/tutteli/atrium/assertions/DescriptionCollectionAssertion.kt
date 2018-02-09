package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
@Deprecated(
    "use the description from package translations, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionCollectionAssertion")
)
enum class DescriptionCollectionAssertion(override val value: String) : StringBasedTranslatable {
    HAS_SIZE(ch.tutteli.atrium.translations.DescriptionCollectionAssertion.HAS_SIZE.value),
    EMPTY(ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY.value),
}
