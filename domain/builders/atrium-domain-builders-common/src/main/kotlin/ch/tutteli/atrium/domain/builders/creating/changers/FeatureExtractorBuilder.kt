package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor.*
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [NewFeatureAssertions] but differs in the intended usage.
 * [NewFeatureAssertions] are intended to make assertions about a return value of a method call or a property,
 * assuming that the call as such always succeeds (no exception is thrown).
 * The [FeatureExtractorBuilder] on the other hand should be used if it is already known,
 * that the call/access fails depending on given arguments.
 * For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
interface FeatureExtractorBuilder {

    companion object {
        /**
         * Entry point to use the [FeatureExtractorBuilder].
         */
        fun <T> create(originalAssertionContainer: Expect<T>): DescriptionStep<T> =
            DescriptionStep.create(originalAssertionContainer)
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
        val originalAssertionContainer: Expect<T>

        /**
         * Uses [coreFactory].[newMethodCallFormatter][CoreFactory.newMethodCallFormatter] to create a description
         * of a method call with the given [methodName] and the given [arguments].
         *
         * Use [withDescription] in case the feature extraction is not based on a method call.
         */
        fun methodCall(methodName: String, vararg arguments: Any?): RepresentationInCaseOfFailureStep<T> =
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
            fun <T> create(
                originalAssertionContainer: Expect<T>
            ): DescriptionStep<T> = DescriptionStepImpl(originalAssertionContainer)
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
        val originalAssertionContainer: Expect<T>

        /**
         * The previously specified description which describes the kind of feature extraction.
         */
        val description: Translatable

        /**
         * Uses [translatable] as representation which will be used in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(translatable: Translatable): FeatureExtractionStep<T> =
            withRepresentationForFailure(RawString.create(translatable))

        /**
         * Uses the given [representationProvider], by turning it into a [LazyRepresentation],
         * to get the representation which will be used in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(representationProvider: () -> Any?): FeatureExtractionStep<T> =
            withRepresentationForFailure(LazyRepresentation(representationProvider))

        /**
         * Uses the given [representation] in case the extraction cannot be performed.
         *
         * Notice, if you want to use text (e.g. a [String]), then wrap it into a [RawString] via [RawString.create]
         * and pass the [RawString] instead.
         */
        fun withRepresentationForFailure(representation: Any): FeatureExtractionStep<T>

        companion object {
            /**
             * Creates a [RepresentationInCaseOfFailureStep] in the context of the [FeatureExtractorBuilder].
             */
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable
            ): RepresentationInCaseOfFailureStep<T> =
                RepresentationInCaseOfFailureStepImpl(originalAssertionContainer, description)
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
        val originalAssertionContainer: Expect<T>

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
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable,
                representationForFailure: Any
            ): FeatureExtractionStep<T> = FeatureExtractionStepImpl(
                originalAssertionContainer, description, representationForFailure
            )
        }
    }

    /**
     * Step which allows to override previously defined properties -- such as use a different description -- but
     * also allows to define options where usually a default value is used, such as use the subject itself as
     * representation.
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
         * Allows to define the [FeatureOptions] via an [OptionsChooser]-lambda which provides convenience functions.
         *
         * The function usually start with `with...` and are sometimes overloaded to ease the configuration.
         */
        fun withOptions(configuration: OptionsChooser<R>.() -> Unit): FinalStep<T, R> =
            withOptions(FeatureOptions(configuration))

        /**
         * Uses the given [expectOptions].
         */
        fun withOptions(expectOptions: FeatureOptions<R>): FinalStep<T, R>

        /**
         * States explicitly that no optional [FeatureOptions] are defined, which means, `build` will create
         * a new [Expect] based on the previously defined mandatory options but without any optional options or
         * in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [FeatureOptions] such as, override the
         * description, define an own representation etc.
         */
        fun withoutOptions(): FinalStep<T, R>

        companion object {
            fun <T, R> create(
                featureExtractionStep: FeatureExtractionStep<T>,
                featureExtraction: (T) -> Option<R>
            ): OptionsStep<T, R> = OptionsStepImpl(featureExtractionStep, featureExtraction)
        }
    }

    /**
     * Helper lambda to specify [FeatureOptions] via convenience methods.
     *
     * Calling multiple times the same method overrides the previously defined value.
     */
    interface OptionsChooser<R> {

        /**
         * Wraps the given [description] into an [Untranslatable] and passes it to the overload
         * which expects a [Translatable] -- this is then used as custom description
         * instead of the previously defined description.
         *
         */
        fun withDescription(description: String) {
            withDescription(Untranslatable(description))
        }

        /**
         * Uses the given [description] as custom description instead of the previously defined description.
         */
        fun withDescription(description: Translatable)

        /**
         * Wraps the given [representation] into a [RawString] and uses it as representation of the subject
         * instead of the so far defined representation (which defaults to the subject as such).
         */
        @Suppress("PublicApiImplicitType" /* fine withSubjectBasedRepresentation defines it */)
        fun withTextRepresentation(representation: String) = withRepresentation(RawString.create(representation))

        /**
         * Uses the given [representation] as representation of the subject instead of
         * the so far defined representation (which defaults to the subject as such).
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then use [withTextRepresentation] instead.
         */
        @Suppress("PublicApiImplicitType" /* fine withSubjectBasedRepresentation defines it */)
        fun withRepresentation(representation: Any) = withSubjectBasedRepresentation { representation }


        /**
         * Uses the given [representationProvider] which provides the representation for a given subject wrapped in
         * [Option] instead of the so far defined representation (which defaults to the subject as such).
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withSubjectBasedRepresentation(representationProvider: (R) -> Any)

        companion object {
            fun <R> createAndBuild(configuration: OptionsChooser<R>.() -> Unit): FeatureOptions<R> =
                OptionsChooserImpl<R>().apply(configuration).build()
        }
    }

    /**
     * Final step in the extract-feature-process, creates a [ExtractedFeaturePostStep]
     * based on the previously specified options.
     *
     * @param T the type of the current subject.
     * @param R the type of the feature, aka the new subject.
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
         * Either the previously specified [FeatureOptions] or `null`.
         */
        val featureOptions: FeatureOptions<R>?

        /**
         * Finishes the `feature extraction`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return An [ExtractedFeaturePostStep] which allows to define what should happen with the new [Expect].
         */
        fun build(): ExtractedFeaturePostStep<T, R>

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [FeatureExtractorBuilder].
             */
            fun <T, R> create(
                featureExtractionStep: FeatureExtractionStep<T>,
                featureExtraction: (T) -> Option<R>,
                featureOptions: FeatureOptions<R>?
            ): FinalStep<T, R> = FinalStepImpl(
                featureExtractionStep, featureExtraction, featureOptions
            )
        }
    }
}


/**
 * Additional (non-mandatory) options for the [FeatureExtractorBuilder] to create an [Expect].
 *
 * @property description Defines a custom description if not null.
 * @property representationInsteadOfFeature Defines a custom representation for the subject if not null.
 */
//TODO #279 remove nullRepresentation
data class FeatureOptions<R>(
    val description: Translatable? = null,
    val representationInsteadOfFeature: ((R) -> Any)? = null,
    val nullRepresentation: Any? = null
) {
    /**
     * Merges the given [options] with this object creating a new [FeatureOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfFeature] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [FeatureOptions] will have the
     * [representationInsteadOfFeature] of [options].
     */
    fun merge(options: FeatureOptions<R>): FeatureOptions<R> =
        FeatureOptions(
            options.description ?: description,
            options.representationInsteadOfFeature ?: representationInsteadOfFeature,
            options.nullRepresentation ?: nullRepresentation
        )
}

@Suppress("FunctionName")
fun <R> FeatureOptions(configuration: FeatureExtractorBuilder.OptionsChooser<R>.() -> Unit): FeatureOptions<R> =
    FeatureExtractorBuilder.OptionsChooser.createAndBuild(configuration)
