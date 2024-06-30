package ch.tutteli.atrium.domain.creating.transformers.impl.builders.featureextractor

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.transformers.featureExtractor
import ch.tutteli.atrium.domain.creating.transformers.impl.BaseTransformationExecutionStep
import ch.tutteli.atrium.reporting.reportables.InlineElement

class DescriptionStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>
) : FeatureExtractorBuilder.DescriptionStep<SubjectT> {

    override fun withDescription(
        description: InlineElement
    ): FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<SubjectT> =
        FeatureExtractorBuilder.RepresentationInCaseOfFailureStep(container, description)
}

internal class RepresentationInCaseOfFailureStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>,
    override val description: InlineElement
) : FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<SubjectT> {
    override fun withRepresentationForFailure(representation: Any): FeatureExtractorBuilder.FeatureExtractionStep<SubjectT> =
        FeatureExtractorBuilder.FeatureExtractionStep(container, description, representation)
}

class FeatureExtractionStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>,
    override val description: InlineElement,
    override val representationForFailure: Any
) : FeatureExtractorBuilder.FeatureExtractionStep<SubjectT> {

    override fun <FeatureT> withFeatureExtraction(extraction: (SubjectT) -> Option<FeatureT>): FeatureExtractorBuilder.OptionsStep<SubjectT, FeatureT> =
        FeatureExtractorBuilder.OptionsStep(this, extraction)
}

class OptionsStepImpl<SubjectT, FeatureT>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<SubjectT>,
    override val featureExtraction: (SubjectT) -> Option<FeatureT>
) : FeatureExtractorBuilder.OptionsStep<SubjectT, FeatureT> {

    @ExperimentalNewExpectTypes
    override fun withOptions(expectOptions: FeatureExpectOptions<FeatureT>): FeatureExtractorBuilder.FinalStep<SubjectT, FeatureT> =
        createFinalStep(expectOptions)

    @OptIn(ExperimentalNewExpectTypes::class)
    override fun withoutOptions(): FeatureExtractorBuilder.FinalStep<SubjectT, FeatureT> =
        createFinalStep(FeatureExpectOptions())

    @ExperimentalNewExpectTypes
    private fun createFinalStep(featureExpectOptions: FeatureExpectOptions<FeatureT>) =
        FeatureExtractorBuilder.FinalStep(
            featureExtractionStep,
            featureExtraction,
            featureExpectOptions
        )
}

@OptIn(ExperimentalNewExpectTypes::class)
class FinalStepImpl<SubjectT, FeatureT>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<SubjectT>,
    override val featureExtraction: (SubjectT) -> Option<FeatureT>,
    override val featureExpectOptions: FeatureExpectOptions<FeatureT>
) : FeatureExtractorBuilder.FinalStep<SubjectT, FeatureT> {

    override fun build(): FeatureExtractorBuilder.ExecutionStep<SubjectT, FeatureT> =
        FeatureExtractorBuilder.ExecutionStep(
            featureExtractionStep.container,
            action = { container -> extractIt(container, None) },
            actionAndApply = { container, expectationCreatorWithUsageHints ->
                extractIt(container, Some(expectationCreatorWithUsageHints))
            }
        )

    private fun extractIt(
        container: ProofContainer<SubjectT>,
        maybeSubExpectationCreatorAndUsageHints: Option<ExpectationCreatorWithUsageHints<FeatureT>>
    ): FeatureExpect<SubjectT, FeatureT> =
        container.featureExtractor.extract(
            container,
            featureExtractionStep.description,
            featureExtractionStep.representationForFailure,
            featureExtraction,
            maybeSubExpectationCreatorAndUsageHints,
            featureExpectOptions
        )
}

class ExecutionStepImpl<SubjectT, FeatureT>(
    container: ProofContainer<SubjectT>,
    action: ProofContainer<SubjectT>.() -> FeatureExpect<SubjectT, FeatureT>,
    actionAndApply: ProofContainer<SubjectT>.(ExpectationCreatorWithUsageHints<FeatureT>) -> Expect<FeatureT>
) : FeatureExtractorBuilder.ExecutionStep<SubjectT, FeatureT>,
    BaseTransformationExecutionStep<SubjectT, FeatureT, FeatureExpect<SubjectT, FeatureT>>(
        container,
        action,
        actionAndApply
    )
