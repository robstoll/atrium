@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which postulate that a
 * [AssertionPlant.subject][SubjectProvider.subject] of type `T` can be transformed (usually down-casting or unboxing) to `TSub`.
 */
@Deprecated(
    "Use the description from package translations; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion")
)
enum class DescriptionTypeTransformationAssertion(override val value: String) : StringBasedTranslatable {
    IS_A(ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion.IS_A.value),
    WARNING_DOWN_CAST_FAILED(ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion.WARNING_DOWN_CAST_FAILED.value),
}
