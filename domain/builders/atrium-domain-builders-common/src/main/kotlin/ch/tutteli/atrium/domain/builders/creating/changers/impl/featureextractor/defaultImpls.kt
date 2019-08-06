package ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.changers.featureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : FeatureExtractorBuilder.DescriptionOption<T> {

    override fun withDescription(
        translatable: Translatable
    ): FeatureExtractorBuilder.RepresentationInCaseOfFailureOption<T> =
        FeatureExtractorBuilder.RepresentationInCaseOfFailureOption.create(originalAssertionContainer, translatable)
}

internal class RepresentationInCaseOfFailureOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable
) : FeatureExtractorBuilder.RepresentationInCaseOfFailureOption<T> {
    override fun withRepresentationForFailure(representation: Any): FeatureExtractorBuilder.CheckOption<T> =
        FeatureExtractorBuilder.CheckOption.create(originalAssertionContainer, description, representation)
}

class CheckOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable,
    override val representationForFailure: Any
) : FeatureExtractorBuilder.CheckOption<T> {

    override fun withCheck(canBeTransformed: (T) -> Boolean): FeatureExtractorBuilder.FeatureExtractionOption<T> =
        FeatureExtractorBuilder.FeatureExtractionOption.create(this, canBeTransformed)
}

class FeatureExtractionOptionImpl<T>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean
) : FeatureExtractorBuilder.FeatureExtractionOption<T> {

    override fun <R> withFeatureExtraction(extraction: (T) -> R): FeatureExtractorBuilder.RepresentationOption<T, R> =
        FeatureExtractorBuilder.RepresentationOption.create(checkOption, canBeExtracted, extraction)
}

class RepresentationOptionImpl<T, R>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean,
    override val featureExtraction: (T) -> R
) : FeatureExtractorBuilder.RepresentationOption<T, R> {

    override fun withRepresentationInsteadOfFeature(representation: Any): FeatureExtractorBuilder.FinalStep<T, R> =
        createFinalStep(representation)

    override fun build(): ExtractedFeaturePostStep<T, R> = createFinalStep(null).build()

    private fun createFinalStep(representation: Any?) =
        FeatureExtractorBuilder.FinalStep.create(
            checkOption,
            canBeExtracted,
            featureExtraction,
            representation
        )
}

class FinalStepImpl<T, R>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean,
    override val featureExtraction: (T) -> R,
    override val representationInsteadOfFeature: Any?
) : FeatureExtractorBuilder.FinalStep<T, R> {

    override fun build(): ExtractedFeaturePostStep<T, R> =
        ExtractedFeaturePostStep(checkOption.originalAssertionContainer,
            extract = { extractIt(this, None) },
            extractAndApply = { assertionCreator -> extractIt(this, Some(assertionCreator)) }
        )

    private fun extractIt(expect: Expect<T>, subAssertions: Option<Expect<R>.() -> Unit>) =
        featureExtractor.extract(
            expect,
            checkOption.description,
            checkOption.representationForFailure,
            canBeExtracted,
            featureExtraction,
            subAssertions,
            representationInsteadOfFeature
        )
}

