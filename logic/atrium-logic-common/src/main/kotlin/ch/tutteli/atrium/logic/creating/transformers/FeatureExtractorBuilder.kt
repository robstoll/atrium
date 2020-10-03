package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.creating.transformers.impl.featureextractor.*
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Helps in using [FeatureExtractor] by providing a guide to set the different parameters in form of a fluent builder.
 */
interface FeatureExtractorBuilder {

    companion object {
        /**
         * Entry point to use the [FeatureExtractorBuilder].
         */
        operator fun <T> invoke(assertionContainer: AssertionContainer<T>): DescriptionStep<T> =
            DescriptionStep(assertionContainer)
    }

    /**
     * Step which allows to specify the description which will be used to describe the feature.
     *
     * @param T the type of the current subject.
     */
    interface DescriptionStep<T> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<T>

        /**
         * Uses [coreFactory].[newMethodCallFormatter][CoreFactory.newMethodCallFormatter] to create a description
         * of a method call with the given [methodName] and the given [arguments].
         *
         * Use [withDescription] in case the feature extraction is not based on a method call.
         */
        fun methodCall(methodName: String, vararg arguments: Any?): RepresentationInCaseOfFailureStep<T> =
            //TODO use methodCallFormatter from container 0.15.0
            withDescription(coreFactory.newMethodCallFormatter().formatCall(methodName, arguments))

        /**
         * Uses the given [description], wraps it into an [Untranslatable] and uses it as description of the feature.
         */
        fun withDescription(description: String): RepresentationInCaseOfFailureStep<T> =
            withDescription(Untranslatable(description))

        /**
         * Uses the given [translatable] as description of the feature.
         */
        fun withDescription(translatable: Translatable): RepresentationInCaseOfFailureStep<T>

        companion object {
            /**
             * Creates a [DescriptionStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <T> invoke(container: AssertionContainer<T>): DescriptionStep<T> =
                DescriptionStepImpl(container)
        }
    }

    /**
     * Step which allows to to define the representation which shall be used
     * in case the extraction cannot be performed.
     *
     * @param T the type of the current subject.
     */
    interface RepresentationInCaseOfFailureStep<T> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<T>

        /**
         * The previously specified description which describes the kind of feature extraction.
         */
        val description: Translatable

        /**
         * Uses the given [representationProvider], by turning it into a [LazyRepresentation],
         * to get the representation which will be used in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(representationProvider: () -> Any?): FeatureExtractionStep<T> =
            withRepresentationForFailure(LazyRepresentation(representationProvider))

        /**
         * Uses the given [representation] in case the extraction cannot be performed.
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         */
        fun withRepresentationForFailure(representation: Any): FeatureExtractionStep<T>

        companion object {
            /**
             * Creates a [RepresentationInCaseOfFailureStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <T> invoke(
                container: AssertionContainer<T>,
                description: Translatable
            ): RepresentationInCaseOfFailureStep<T> =
                RepresentationInCaseOfFailureStepImpl(container, description)
        }
    }

    /**
     * Step to define the feature extraction as such where a one can include a check by returning [None] in case the
     * extraction should not be carried out.
     *
     * @param T the type of the current subject.
     */
    interface FeatureExtractionStep<T> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<T>

        /**
         * The previously specified description which describes the kind of feature extraction.
         */

        val description: Translatable

        /**
         * The previously specified representation which will be used in case the feature cannot be extracted.
         */
        val representationForFailure: Any

        /**
         * Defines the feature extraction as such which is most likely based on the current subject
         * (but does not need to be).
         *
         * @param extraction A function returning either [Some] with the corresponding feature or [None] in case the
         *   extraction cannot be carried out.
         */
        fun <R> withFeatureExtraction(extraction: (subject: T) -> Option<R>): OptionsStep<T, R>

        companion object {
            /**
             * Creates a [FeatureExtractionStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <T> invoke(
                container: AssertionContainer<T>,
                description: Translatable,
                representationForFailure: Any
            ): FeatureExtractionStep<T> = FeatureExtractionStepImpl(
                container, description, representationForFailure
            )
        }
    }

    /**
     * Step which allows to override previously defined properties -- such as: use a different description -- but
     * also allows to define options where usually a default value is used -- e.g. per default the subject itself is
     * use as representation, this can be changed.
     *
     * @param T the type of the subject.
     */
    interface OptionsStep<T, R> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         */
        val featureExtractionStep: FeatureExtractionStep<T>

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (T) -> Option<R>

        /**
         * Allows to define the [FeatureExpectOptions] via an [FeatureExpectOptionsChooser]-lambda
         * which provides convenience functions.
         *
         * A convenience function usually starts with `with...` and is sometimes overloaded to ease the configuration.
         */
        @ExperimentalNewExpectTypes
        fun withOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FinalStep<T, R> =
            withOptions(FeatureExpectOptions(configuration))

        /**
         * Uses the given [expectOptions].
         */
        @ExperimentalNewExpectTypes
        fun withOptions(expectOptions: FeatureExpectOptions<R>): FinalStep<T, R>

        /**
         * States explicitly that no optional [FeatureExpectOptions] are defined, which means, the [FeatureExtractor]
         * will create a new [AssertionContainer] based on the previously defined mandatory options
         * but without any optional options or in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [FeatureExpectOptions] such as, override the
         * description, define an own representation etc.
         */
        fun withoutOptions(): FinalStep<T, R>

        companion object {
            operator fun <T, R> invoke(
                featureExtractionStep: FeatureExtractionStep<T>,
                featureExtraction: (T) -> Option<R>
            ): OptionsStep<T, R> = OptionsStepImpl(featureExtractionStep, featureExtraction)
        }
    }

    /**
     * Final step in the help-me-to-call-[FeatureExtractor]-process, which creates an [ExecutionStep] incorporating all
     * chosen options and is able to call [SubjectChanger] accordingly.
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FinalStep<T, R> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         */
        val featureExtractionStep: FeatureExtractionStep<T>

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (T) -> Option<R>

        /**
         * Either the previously specified [FeatureExpectOptions] or `null`.
         */
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class)
        val featureExpectOptions: FeatureExpectOptions<R>?

        /**
         * Finishes the help-me-to-call-[FeatureExtractor]-process by creating an [ExecutionStep] incorporating all
         * previously chosen options.
         *
         * @return The [ExecutionStep] which allows to define how the change shall be carried out.
         */
        fun build(): ExecutionStep<T, R>

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [FeatureExtractorBuilder].
             */
            @ExperimentalNewExpectTypes
            operator fun <T, R> invoke(
                featureExtractionStep: FeatureExtractionStep<T>,
                featureExtraction: (T) -> Option<R>,
                featureExpectOptions: FeatureExpectOptions<R>
            ): FinalStep<T, R> = FinalStepImpl(
                featureExtractionStep, featureExtraction, featureExpectOptions
            )
        }
    }

    /**
     * Step which allows to decide how the transformation shall be executed.
     *
     * For instance, if it shall just perform the feature extraction and return the new [Expect] of type [R]
     * or if it shall pass an assertionCreator-lambda which creates sub-assertions etc.
     */
    interface ExecutionStep<T, R> : TransformationExecutionStep<T, R, FeatureExpect<T, R>> {

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T, R> invoke(
                container: AssertionContainer<T>,
                action: (AssertionContainer<T>) -> FeatureExpect<T, R>,
                actionAndApply: (AssertionContainer<T>, Expect<R>.() -> Unit) -> Expect<R>
            ): ExecutionStep<T, R> = ExecutionStepImpl(container, action, actionAndApply)
        }
    }

}

