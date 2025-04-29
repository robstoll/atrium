package ch.tutteli.atrium.creating.transformers

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.transformers.impl.builders.featureextractor.*
import ch.tutteli.atrium.creating.transformers.impl.builders.featureextractor.RepresentationInCaseOfFailureStepImpl
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.Text

/**
 * Helps in using [FeatureExtractor] by providing a guide to set the different parameters in form of a fluent builder.
 *
 * @since 1.3.0
 */
interface FeatureExtractorBuilder {

    /**
     * Extension point for [FeatureExtractorBuilder].
     *
     * @since 1.3.0
     */
    companion object {
        /**
         * Entry point to use the [FeatureExtractorBuilder].
         *
         * @since 1.3.0
         */
        //TODO 1.3.0 decide what we want, I think this is not consistent, sometimes we use invoke and sometimes we
        // use create...
        operator fun <SubjectT> invoke(proofContainer: ProofContainer<SubjectT>): DescriptionStep<SubjectT> =
            DescriptionStep(proofContainer)
    }

    /**
     * Step which allows to specify the description which will be used to describe the feature.
     *
     * @param SubjectT the type of the current subject.
     *
     * @since 1.3.0
     */
    interface DescriptionStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * Uses the given [description] as description of the feature.
         *
         * @since 1.3.0
         */
        fun withDescription(description: InlineElement): RepresentationInCaseOfFailureStep<SubjectT>

        /**
         * Extension point for [DescriptionStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [DescriptionStep] in the context of the [FeatureExtractorBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT> invoke(container: ProofContainer<SubjectT>): DescriptionStep<SubjectT> =
                DescriptionStepImpl(container)
        }
    }

    /**
     * Step which allows to define the representation which shall be used
     * in case the extraction cannot be performed.
     *
     * @param SubjectT the type of the current subject.
     *
     * @since 1.3.0
     */
    interface RepresentationInCaseOfFailureStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * The previously specified description which describes the kind of feature extraction.
         *
         * @since 1.3.0
         */
        val description: InlineElement

        /**
         * Uses the given [representationProvider], by turning it into a [LazyRepresentation],
         * to get the representation which will be used in case the extraction cannot be performed.
         *
         * @since 1.3.0
         */
        fun withRepresentationForFailure(representationProvider: () -> Any?): FeatureExtractionStep<SubjectT> =
            withRepresentationForFailure(LazyRepresentation(representationProvider))

        /**
         * Uses the given [representation] in case the extraction cannot be performed.
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         *
         * @since 1.3.0
         */
        fun withRepresentationForFailure(representation: Any): FeatureExtractionStep<SubjectT>

        /**
         * Extension point for [RepresentationInCaseOfFailureStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [RepresentationInCaseOfFailureStep] in the context of the [FeatureExtractorBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT> invoke(
                container: ProofContainer<SubjectT>,
                description: InlineElement
            ): RepresentationInCaseOfFailureStep<SubjectT> =
                RepresentationInCaseOfFailureStepImpl(container, description)
        }
    }

    /**
     * Step to define the feature extraction as such where a one can include a check by returning [None] in case the
     * extraction should not be carried out.
     *
     * @param SubjectT the type of the current subject.
     *
     * @since 1.3.0
     */
    interface FeatureExtractionStep<SubjectT> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * The previously specified description which describes the kind of feature extraction.
         *
         * @since 1.3.0
         */
        val description: InlineElement

        /**
         * The previously specified representation which will be used in case the feature cannot be extracted.
         *
         * @since 1.3.0
         */
        val representationForFailure: Any

        /**
         * Defines the feature extraction as such which is most likely based on the current subject
         * (but does not need to be).
         *
         * @param extraction A function returning either [Some] with the corresponding feature or [None] in case the
         *   extraction cannot be carried out.
         *
         * @since 1.3.0
         */
        fun <FeatureT> withFeatureExtraction(extraction: (subject: SubjectT) -> Option<FeatureT>): OptionsStep<SubjectT, FeatureT>

        /**
         * Extension point for [FeatureExtractionStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [FeatureExtractionStep] in the context of the [FeatureExtractorBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT> invoke(
                container: ProofContainer<SubjectT>,
                description: InlineElement,
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
     *
     * @since 1.3.0
     */
    interface OptionsStep<SubjectT, FeatureT> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         *
         * @since 1.3.0
         */
        val featureExtractionStep: FeatureExtractionStep<SubjectT>

        /**
         * The previously specified feature extraction lambda.
         *
         * @since 1.3.0
         */
        val featureExtraction: (SubjectT) -> Option<FeatureT>

        /**
         * Allows to define the [FeatureExpectOptions] via an [FeatureExpectOptionsChooser]-lambda
         * which provides convenience functions.
         *
         * A convenience function usually starts with `with...`.
         *
         * @since 1.3.0
         */
        @ExperimentalNewExpectTypes
        fun withOptions(configuration: FeatureExpectOptionsChooser<FeatureT>.() -> Unit): FinalStep<SubjectT, FeatureT> =
            withOptions(FeatureExpectOptions(configuration))


        /**
         * Uses the given [expectOptions].
         *
         * @since 1.3.0
         */
        @ExperimentalNewExpectTypes
        fun withOptions(expectOptions: FeatureExpectOptions<FeatureT>): FinalStep<SubjectT, FeatureT>

        /**
         * States explicitly that no optional [FeatureExpectOptions] are defined, which means, the [FeatureExtractor]
         * will create a new [ProofContainer] based on the previously defined mandatory options
         * but without any optional options or in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [FeatureExpectOptions] such as, override the
         * description, define an own representation etc.
         *
         * @since 1.3.0
         */
        fun withoutOptions(): FinalStep<SubjectT, FeatureT>

        /**
         * Extension point for [OptionsStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates an [OptionsStep].
             *
             * @since 1.3.0
             */
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
     *
     * @since 1.3.0
     */
    interface FinalStep<SubjectT, FeatureT> {
        /**
         * The so far chosen options up to the [FeatureExtractionStep] step.
         *
         * @since 1.3.0
         */
        val featureExtractionStep: FeatureExtractionStep<SubjectT>

        /**
         * The previously specified feature extraction lambda.
         *
         * @since 1.3.0
         */
        val featureExtraction: (SubjectT) -> Option<FeatureT>

        /**
         * Either the previously specified [FeatureExpectOptions] or `null`.
         *
         * @since 1.3.0
         */
        @OptIn(ExperimentalNewExpectTypes::class)
        val featureExpectOptions: FeatureExpectOptions<FeatureT>?

        /**
         * Finishes the help-me-to-call-[FeatureExtractor]-process by creating an [ExecutionStep] incorporating all
         * previously chosen options.
         *
         * @return The [ExecutionStep] which allows to define how the change shall be carried out.
         *
         * @since 1.3.0
         */
        fun build(): ExecutionStep<SubjectT, FeatureT>

        /**
         * Extension point for [FinalStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates the [FinalStep] in the context of the [FeatureExtractorBuilder].
             *
             * @since 1.3.0
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
     *
     * @since 1.3.0
     */
    interface ExecutionStep<SubjectT, FeatureT> :
        TransformationExecutionStep<SubjectT, FeatureT, FeatureExpect<SubjectT, FeatureT>> {

        /**
         * Extension point for [ExecutionStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT, FeatureT> invoke(
                container: ProofContainer<SubjectT>,
                action: (ProofContainer<SubjectT>) -> FeatureExpect<SubjectT, FeatureT>,
                actionAndApply: (ProofContainer<SubjectT>, ExpectationCreatorWithUsageHints<FeatureT>) -> Expect<FeatureT>
            ): ExecutionStep<SubjectT, FeatureT> = ExecutionStepImpl(container, action, actionAndApply)
        }
    }

}

