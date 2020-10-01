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
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionStepImpl<T>(
    override val container: AssertionContainer<T>
) : FeatureExtractorBuilder.DescriptionStep<T> {

    override fun withDescription(
        translatable: Translatable
    ): FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<T> =
        FeatureExtractorBuilder.RepresentationInCaseOfFailureStep(container, translatable)
}

internal class RepresentationInCaseOfFailureStepImpl<T>(
    override val container: AssertionContainer<T>,
    override val description: Translatable
) : FeatureExtractorBuilder.RepresentationInCaseOfFailureStep<T> {
    override fun withRepresentationForFailure(representation: Any): FeatureExtractorBuilder.FeatureExtractionStep<T> =
        FeatureExtractorBuilder.FeatureExtractionStep(container, description, representation)
}

class FeatureExtractionStepImpl<T>(
    override val container: AssertionContainer<T>,
    override val description: Translatable,
    override val representationForFailure: Any
) : FeatureExtractorBuilder.FeatureExtractionStep<T> {

    override fun <R> withFeatureExtraction(extraction: (T) -> Option<R>): FeatureExtractorBuilder.OptionsStep<T, R> =
        FeatureExtractorBuilder.OptionsStep(this, extraction)
}

class OptionsStepImpl<T, R>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<T>,
    override val featureExtraction: (T) -> Option<R>
) : FeatureExtractorBuilder.OptionsStep<T, R> {

    @ExperimentalNewExpectTypes
    override fun withOptions(expectOptions: FeatureExpectOptions<R>): FeatureExtractorBuilder.FinalStep<T, R> =
        createFinalStep(expectOptions)

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override fun withoutOptions(): FeatureExtractorBuilder.FinalStep<T, R> = createFinalStep(FeatureExpectOptions())

    @ExperimentalNewExpectTypes
    private fun createFinalStep(featureExpectOptions: FeatureExpectOptions<R>) =
        FeatureExtractorBuilder.FinalStep(
            featureExtractionStep,
            featureExtraction,
            featureExpectOptions
        )
}

class FinalStepImpl<T, R>(
    override val featureExtractionStep: FeatureExtractorBuilder.FeatureExtractionStep<T>,
    override val featureExtraction: (T) -> Option<R>,
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override val featureExpectOptions: FeatureExpectOptions<R>
) : FeatureExtractorBuilder.FinalStep<T, R> {

    override fun build(): FeatureExtractorBuilder.ExecutionStep<T, R> =
        FeatureExtractorBuilder.ExecutionStep(
            featureExtractionStep.container,
            action = { container -> extractIt(container, None) },
            actionAndApply = { container, assertionCreator -> extractIt(container, Some(assertionCreator)) }
        )

    private fun extractIt(container: AssertionContainer<T>, maybeSubAssertions: Option<Expect<R>.() -> Unit>): FeatureExpect<T, R> =
        container.featureExtractor.extract(
            container,
            featureExtractionStep.description,
            featureExtractionStep.representationForFailure,
            featureExtraction,
            maybeSubAssertions,
            featureExpectOptions
        )
}

class ExecutionStepImpl<T, R>(
    container: AssertionContainer<T>,
    action: AssertionContainer<T>.() -> FeatureExpect<T, R>,
    actionAndApply: AssertionContainer<T>.(Expect<R>.() -> Unit) -> Expect<R>
) : FeatureExtractorBuilder.ExecutionStep<T, R>,
    BaseTransformationExecutionStep<T, R, FeatureExpect<T, R>>(container, action, actionAndApply)
