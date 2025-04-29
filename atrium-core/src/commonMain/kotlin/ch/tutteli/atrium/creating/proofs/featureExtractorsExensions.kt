//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.extractFeature
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.feature.MetaFeature
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.ErrorMessages


fun <T, R> ProofContainer<T>.genericSubjectBasedFeature(
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
    container: ProofContainer<T>,
    metaFeature: MetaFeature<R>
): FeatureExtractorBuilder.ExecutionStep<T, R> {
    val representation: Any = metaFeature.representation ?: Text.NULL
    @OptIn(ExperimentalNewExpectTypes::class)
    return container.extractFeature
        .withDescription(metaFeature.description)
        .withRepresentationForFailure(representation)
        .withFeatureExtraction { metaFeature.maybeSubject }
        .withOptions { withRepresentationIfSubjectDefined { representation } }
        .build()
}
