package ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.changers.featureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : FeatureExtractorBuilder.DescriptionOption<T> {

    override fun withDescription(translatable: Translatable): FeatureExtractorBuilder.RepresentationInCaseOfFailureOption<T>
        = FeatureExtractorBuilder.RepresentationInCaseOfFailureOption.create(originalAssertionContainer, translatable)
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

    override fun <R> withFeatureExtraction(extraction: (T) -> R): FeatureExtractorBuilder.SubAssertionOption<T, R> =
        FeatureExtractorBuilder.SubAssertionOption.create(checkOption, canBeExtracted, extraction)
}

class SubAssertionOptionImpl<T, R>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean,
    override val featureExtraction: (T) -> R
) : FeatureExtractorBuilder.SubAssertionOption<T, R> {

    override fun withoutSubAssertions(): FeatureExtractorBuilder.RepresentationOption<T, R> =
        FeatureExtractorBuilder.RepresentationOption.create(checkOption, canBeExtracted, featureExtraction, null)

    override fun withSubAssertions(assertionCreator: Expect<R>.() -> Unit): FeatureExtractorBuilder.RepresentationOption<T, R> =
        FeatureExtractorBuilder.RepresentationOption.create(checkOption, canBeExtracted, featureExtraction, assertionCreator)
}

class RepresentationOptionImpl<T, R>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean,
    override val featureExtraction: (T) -> R,
    override val subAssertions: (Expect<R>.() -> Unit)?
) : FeatureExtractorBuilder.RepresentationOption<T, R> {

    override fun withRepresentationInsteadOfFeature(representation: Any): FeatureExtractorBuilder.FinalStep<T, R> =
        createFinalStep(representation)

    override fun build(): Expect<R> = createFinalStep(null).build()

    private fun createFinalStep(representation: Any?) =
        FeatureExtractorBuilder.FinalStep.create(
            checkOption,
            canBeExtracted,
            featureExtraction,
            subAssertions,
            representation
        )
}

class FinalStepImpl<T, R>(
    override val checkOption: FeatureExtractorBuilder.CheckOption<T>,
    override val canBeExtracted: (T) -> Boolean,
    override val featureExtraction: (T) -> R,
    override val subAssertions: (Expect<R>.() -> Unit)?,
    override val representationInsteadOfFeature: Any?
) : FeatureExtractorBuilder.FinalStep<T, R> {

    override fun build(): Expect<R> = featureExtractor.extract(
        checkOption.originalAssertionContainer,
        checkOption.description,
        checkOption.representationForFailure,
        canBeExtracted,
        featureExtraction,
        subAssertions,
        representationInsteadOfFeature
    )
}

