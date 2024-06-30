//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers.impl.featureextractor

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.featureExtractor
import ch.tutteli.atrium.logic.creating.transformers.impl.BaseTransformationExecutionStep

class DescriptionStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>
) : FeatureExtractorBuilder.DescriptionStep<SubjectT> {

    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    override fun withDescription(
        translatable: ch.tutteli.atrium.reporting.translating.Translatable
    ): FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<SubjectT> =
        FeatureExtractorBuilder.RepresentationInCaseOfFailureStep(container, translatable)
}

internal class RepresentationInCaseOfFailureStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>,
    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    override val description: ch.tutteli.atrium.reporting.translating.Translatable
) : FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<SubjectT> {
    override fun withRepresentationForFailure(representation: Any): FeatureExtractorBuilder.FeatureExtractionStep<SubjectT> =
        FeatureExtractorBuilder.FeatureExtractionStep(container, description, representation)
}

class FeatureExtractionStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>,
    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    override val description: ch.tutteli.atrium.reporting.translating.Translatable,
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
    override fun withoutOptions(): FeatureExtractorBuilder.FinalStep<SubjectT, FeatureT> = createFinalStep(FeatureExpectOptions())

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
            actionAndApply = { container, assertionCreator -> extractIt(container, Some(assertionCreator)) }
        )

    private fun extractIt(container: AssertionContainer<SubjectT>, maybeSubAssertions: Option<Expect<FeatureT>.() -> Unit>): FeatureExpect<SubjectT, FeatureT> =
        container.featureExtractor.extract(
            container,
            featureExtractionStep.description,
            featureExtractionStep.representationForFailure,
            featureExtraction,
            maybeSubAssertions,
            featureExpectOptions
        )
}

class ExecutionStepImpl<SubjectT, FeatureT>(
    container: AssertionContainer<SubjectT>,
    action: AssertionContainer<SubjectT>.() -> FeatureExpect<SubjectT, FeatureT>,
    actionAndApply: AssertionContainer<SubjectT>.(Expect<FeatureT>.() -> Unit) -> Expect<FeatureT>
) : FeatureExtractorBuilder.ExecutionStep<SubjectT, FeatureT>,
    BaseTransformationExecutionStep<SubjectT, FeatureT, FeatureExpect<SubjectT, FeatureT>>(container, action, actionAndApply)
