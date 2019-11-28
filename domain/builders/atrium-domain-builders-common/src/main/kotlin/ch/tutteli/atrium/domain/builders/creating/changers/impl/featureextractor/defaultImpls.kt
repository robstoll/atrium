package ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.changers.featureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionStepImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : FeatureExtractorBuilder.DescriptionStep<T> {

    override fun withDescription(
        translatable: Translatable
    ): FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<T> =
        FeatureExtractorBuilder.RepresentationInCaseOfFailureStep.create(originalAssertionContainer, translatable)
}

internal class RepresentationInCaseOfFailureStepImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable
) : FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<T> {
    override fun withRepresentationForFailure(representation: Any): FeatureExtractorBuilder.FeatureExtractionStep<T> =
        FeatureExtractorBuilder.FeatureExtractionStep.create(originalAssertionContainer, description, representation)
}

class FeatureExtractionStepImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable,
    override val representationForFailure: Any
) : FeatureExtractorBuilder.FeatureExtractionStep<T> {

    override fun <R> withFeatureExtraction(extraction: (T) -> Option<R>): FeatureExtractorBuilder.OptionalRepresentationStep<T, R> =
        FeatureExtractorBuilder.OptionalRepresentationStep.create(this, extraction)
}

class OptionalRepresentationStepImpl<T, R>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<T>,
    override val featureExtraction: (T) -> Option<R>
) : FeatureExtractorBuilder.OptionalRepresentationStep<T, R> {

    override fun withRepresentationInsteadOfFeature(representation: Any): FeatureExtractorBuilder.FinalStep<T, R> =
        createFinalStep(representation)

    override fun build(): ExtractedFeaturePostStep<T, R> = createFinalStep(null).build()

    private fun createFinalStep(representation: Any?) =
        FeatureExtractorBuilder.FinalStep.create(
            featureExtractionStep,
            featureExtraction,
            representation
        )
}

class FinalStepImpl<T, R>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<T>,
    override val featureExtraction: (T) -> Option<R>,
    override val representationInsteadOfFeature: Any?
) : FeatureExtractorBuilder.FinalStep<T, R> {

    override fun build(): ExtractedFeaturePostStep<T, R> =
        ExtractedFeaturePostStep(featureExtractionStep.originalAssertionContainer,
            extract = { extractIt(this, None) },
            extractAndApply = { assertionCreator -> extractIt(this, Some(assertionCreator)) }
        )

    private fun extractIt(expect: Expect<T>, subAssertions: Option<Expect<R>.() -> Unit>) =
        featureExtractor.extract(
            expect,
            featureExtractionStep.description,
            featureExtractionStep.representationForFailure,
            featureExtraction,
            subAssertions,
            representationInsteadOfFeature
        )
}

