@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionThrowableAssertion")
)
enum class DescriptionThrowableAssertion(override val value: String) : StringBasedTranslatable {
    IS_A(ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_A.value),
    NO_EXCEPTION_OCCURRED(ch.tutteli.atrium.translations.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED.value),
}
