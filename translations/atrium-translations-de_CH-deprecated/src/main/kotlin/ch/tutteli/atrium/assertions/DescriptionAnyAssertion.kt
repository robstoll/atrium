@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
@Deprecated(
    "Use the description from package translations; will be removed with 0.10.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion")
)
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE(DescriptionBasic.TO_BE.value),
    NOT_TO_BE(DescriptionBasic.NOT_TO_BE.value),
    IS_SAME(ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME.value),
    IS_NOT_SAME(ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_NOT_SAME.value),
}
