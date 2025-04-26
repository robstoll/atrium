//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.creating.FeatureExpectOptionsChooser
import ch.tutteli.atrium.logic.creating.transformers.impl.featureextractor.*
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.Text

/**
 * Helps in using [FeatureExtractor] by providing a guide to set the different parameters in form of a fluent builder.
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder")
)
interface FeatureExtractorBuilder {

    companion object {
        /**
         * Entry point to use the [FeatureExtractorBuilder].
         */
        operator fun <SubjectT> invoke(assertionContainer: AssertionContainer<SubjectT>): DescriptionStep<SubjectT> =
            DescriptionStep(assertionContainer)
    }

    /**
     * Step which allows to specify the description which will be used to describe the feature.
     *
     * @param SubjectT the type of the current subject.
     */
    interface DescriptionStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<SubjectT>

        /**
         * Uses the configured [MethodCallFormatter] to create a description
         * of a method call with the given [methodName] and the given [arguments].
         *
         * Use [withDescription] in case the feature extraction is not based on a method call.
         */
        fun methodCall(methodName: String, vararg arguments: Any?): RepresentationInCaseOfFailureStep<SubjectT> =
            withDescription(
                @OptIn(ExperimentalComponentFactoryContainer::class)
                container.components.build<MethodCallFormatter>().formatCall(methodName, arguments)
            )

        /**
         * Uses the given [description], wraps it into an [ch.tutteli.atrium.reporting.translating.Untranslatable] and uses it as description of the feature.
         */
        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        fun withDescription(description: String): RepresentationInCaseOfFailureStep<SubjectT> =
            withDescription(ch.tutteli.atrium.reporting.translating.Untranslatable(description))

        /**
         * Uses the given [translatable] as description of the feature.
         */
        //TODO 1.3.0 replace with Representable and remove suppression
        @Suppress("DEPRECATION")
        fun withDescription(translatable: ch.tutteli.atrium.reporting.translating.Translatable): RepresentationInCaseOfFailureStep<SubjectT>

        companion object {
            /**
             * Creates a [DescriptionStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <SubjectT> invoke(container: AssertionContainer<SubjectT>): DescriptionStep<SubjectT> =
                DescriptionStepImpl(container)
        }
    }

    /**
     * Step which allows to define the representation which shall be used
     * in case the extraction cannot be performed.
     *
     * @param SubjectT the type of the current subject.
     */
    interface RepresentationInCaseOfFailureStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<SubjectT>

        /**
         * The previously specified description which describes the kind of feature extraction.
         */
        //TODO 1.3.0 replace with Representable and remove suppression
        @Suppress("DEPRECATION")
        val description: ch.tutteli.atrium.reporting.translating.Translatable

        /**
         * Uses the given [representationProvider], by turning it into a [LazyRepresentation],
         * to get the representation which will be used in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(representationProvider: () -> Any?): FeatureExtractionStep<SubjectT> =
            withRepresentationForFailure(LazyRepresentation(representationProvider))

        /**
         * Uses the given [representation] in case the extraction cannot be performed.
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         */
        fun withRepresentationForFailure(representation: Any): FeatureExtractionStep<SubjectT>

        companion object {
            /**
             * Creates a [RepresentationInCaseOfFailureStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <SubjectT> invoke(
                container: AssertionContainer<SubjectT>,
                //TODO 1.3.0 remove suppress again, use InlineElement instead
                @Suppress("DEPRECATION")
                description: ch.tutteli.atrium.reporting.translating.Translatable
            ): RepresentationInCaseOfFailureStep<SubjectT> =
                RepresentationInCaseOfFailureStepImpl(container, description)
        }
    }

    /**
     * Step to define the feature extraction as such where a one can include a check by returning [None] in case the
     * extraction should not be carried out.
     *
     * @param SubjectT the type of the current subject.
     */
    interface FeatureExtractionStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val container: AssertionContainer<SubjectT>

        /**
         * The previously specified description which describes the kind of feature extraction.
         */
        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        val description: ch.tutteli.atrium.reporting.translating.Translatable

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
        fun <FeatureT> withFeatureExtraction(extraction: (subject: SubjectT) -> Option<FeatureT>): OptionsStep<SubjectT, FeatureT>

        companion object {
            /**
             * Creates a [FeatureExtractionStep] in the context of the [FeatureExtractorBuilder].
             */
            operator fun <SubjectT> invoke(
                container: AssertionContainer<SubjectT>,
                //TODO 1.3.0 remove suppress again, use InlineElement instead
                @Suppress("DEPRECATION")
                description: ch.tutteli.atrium.reporting.translating.Translatable,
                representationForFailure: Any
            ): FeatureExtractionStep<SubjectT> = FeatureExtractionStepImpl(
                container, description, representationForFailure
            )
        }
    }

    /**
     * Step which allows to override previously defined properties -- such as: use a different description -- but
     * also allows to define options where usually a default value is used -- e.g. per default the subject itself is
     * use as representation, this can be changed.
     *
     * @param SubjectT the type of the subject.
     */
    interface OptionsStep<SubjectT, FeatureT> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         */
        val featureExtractionStep: FeatureExtractionStep<SubjectT>

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (SubjectT) -> Option<FeatureT>

        /**
         * Allows to define the [FeatureExpectOptions] via an [FeatureExpectOptionsChooser]-lambda
         * which provides convenience functions.
         *
         * A convenience function usually starts with `with...` and is sometimes overloaded to ease the configuration.
         */
        @ExperimentalNewExpectTypes
        fun withOptions(configuration: FeatureExpectOptionsChooser<FeatureT>.() -> Unit): FinalStep<SubjectT, FeatureT> =
            withOptions(FeatureExpectOptions(configuration))

        /**
         * Uses the given [expectOptions].
         */
        @ExperimentalNewExpectTypes
        fun withOptions(expectOptions: FeatureExpectOptions<FeatureT>): FinalStep<SubjectT, FeatureT>

        /**
         * States explicitly that no optional [FeatureExpectOptions] are defined, which means, the [FeatureExtractor]
         * will create a new [AssertionContainer] based on the previously defined mandatory options
         * but without any optional options or in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [FeatureExpectOptions] such as, override the
         * description, define an own representation etc.
         */
        fun withoutOptions(): FinalStep<SubjectT, FeatureT>

        companion object {
            operator fun <SubjectT, FeatureT> invoke(
                featureExtractionStep: FeatureExtractionStep<SubjectT>,
                featureExtraction: (SubjectT) -> Option<FeatureT>
            ): OptionsStep<SubjectT, FeatureT> = OptionsStepImpl(featureExtractionStep, featureExtraction)
        }
    }

    /**
     * Final step in the help-me-to-call-[FeatureExtractor]-process, which creates an [ExecutionStep] incorporating all
     * chosen options and is able to call [SubjectChanger] accordingly.
     *
     * @param SubjectT the type of the current subject.
     * @param FeatureT the type of the new subject.
     */
    interface FinalStep<SubjectT, FeatureT> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         */
        val featureExtractionStep: FeatureExtractionStep<SubjectT>

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (SubjectT) -> Option<FeatureT>

        /**
         * Either the previously specified [FeatureExpectOptions] or `null`.
         */
        @OptIn(ExperimentalNewExpectTypes::class)
        val featureExpectOptions: FeatureExpectOptions<FeatureT>?

        /**
         * Finishes the help-me-to-call-[FeatureExtractor]-process by creating an [ExecutionStep] incorporating all
         * previously chosen options.
         *
         * @return The [ExecutionStep] which allows to define how the change shall be carried out.
         */
        fun build(): ExecutionStep<SubjectT, FeatureT>

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [FeatureExtractorBuilder].
             */
            @ExperimentalNewExpectTypes
            operator fun <SubjectT, FeatureT> invoke(
                featureExtractionStep: FeatureExtractionStep<SubjectT>,
                featureExtraction: (SubjectT) -> Option<FeatureT>,
                featureExpectOptions: FeatureExpectOptions<FeatureT>
            ): FinalStep<SubjectT, FeatureT> = FinalStepImpl(
                featureExtractionStep, featureExtraction, featureExpectOptions
            )
        }
    }

    /**
     * Step which allows to decide how the transformation shall be executed.
     *
     * For instance, if it shall just perform the feature extraction and return the new [Expect] of type [FeatureT]
     * or if it shall pass an assertionCreator-lambda which creates sub-assertions etc.
     */
    interface ExecutionStep<SubjectT, FeatureT> : TransformationExecutionStep<SubjectT, FeatureT, FeatureExpect<SubjectT, FeatureT>> {

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <SubjectT, FeatureT> invoke(
                container: AssertionContainer<SubjectT>,
                action: (AssertionContainer<SubjectT>) -> FeatureExpect<SubjectT, FeatureT>,
                actionAndApply: (AssertionContainer<SubjectT>, Expect<FeatureT>.() -> Unit) -> Expect<FeatureT>
            ): ExecutionStep<SubjectT, FeatureT> = ExecutionStepImpl(container, action, actionAndApply)
        }
    }

}

