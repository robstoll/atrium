//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.feature.MetaFeature
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.ErrorMessages

//TODO 1.3.0 deprecate everything

/**
 * Convenience method to pass a [String] as [description] which is wrapped into an [ch.tutteli.atrium.reporting.translating.Untranslatable] instead of passing
 * a [ch.tutteli.atrium.reporting.translating.Translatable].
 */
@Deprecated(
    "Use the overload which expects an InlineElement and wrap your string into a Text, will be removed with 2.0.0 at the latest",
    ReplaceWith("this.manualFeature(Text(description), provider)", "ch.tutteli.atrium.reporting.Text")
)
@Suppress("DEPRECATION")
fun <T, R> AssertionContainer<T>.manualFeature(
    description: String,
    provider: T.() -> R
): FeatureExtractorBuilder.ExecutionStep<T, R> =
    manualFeature(ch.tutteli.atrium.reporting.translating.Untranslatable(description), provider)

@Deprecated(
    "Use featureBasedOnMetaFeature, will be removed with 2.0.0 at the latest",
    ReplaceWith("this.featureBasedOnMetaFeature(provider)", "ch.tutteli.atrium.creating.proofs.featureBasedOnMetaFeature")
)
fun <T, R> AssertionContainer<T>.genericSubjectBasedFeature(
    provider: (T) -> MetaFeature<R>
): FeatureExtractorBuilder.ExecutionStep<T, R> =
    genericFeature(this, maybeSubject.fold(::createFeatureSubjectNotDefined) { provider(it) })

private fun <R> createFeatureSubjectNotDefined(): MetaFeature<R> =
    MetaFeature(
        ErrorMessages.DESCRIPTION_BASED_ON_SUBJECT,
        ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED,
        None
    )

private fun <T, R> genericFeature(
    container: AssertionContainer<T>,
    metaFeature: MetaFeature<R>
): FeatureExtractorBuilder.ExecutionStep<T, R> {
    val representation: Any = metaFeature.representation ?: Text.NULL
    @OptIn(ExperimentalNewExpectTypes::class)
    return container.extractFeature
        .withDescription(metaFeature.description)
        .withRepresentationForFailure(representation)
        .withFeatureExtraction { metaFeature.maybeSubject }
        .withOptions { withRepresentation { representation } }
        .build()
}
