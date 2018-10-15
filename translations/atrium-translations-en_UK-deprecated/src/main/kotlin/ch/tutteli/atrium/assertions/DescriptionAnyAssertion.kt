@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
@Deprecated(
    "use the description from package translations, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion")
)
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE(ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE.value),
    NOT_TO_BE(ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE.value),
    IS_SAME(ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME.value),
    IS_NOT_SAME(ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_NOT_SAME.value),
}
