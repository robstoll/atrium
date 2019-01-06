@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use a different wording in
 * a two different assertion functions.
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionBasic")
)
enum class DescriptionBasic(override val value: String) : StringBasedTranslatable {
    IS(ch.tutteli.atrium.translations.DescriptionBasic.IS.value),
    IS_NOT(ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT.value),
}
