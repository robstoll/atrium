package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor.*
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
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
         * Entry point to use the feature extractor.
         */
        fun <T> builder(originalAssertionContainer: Expect<T>): DescriptionOption<T> =
            DescriptionOption.create(originalAssertionContainer)
    }

    /**
     * Option step which allows to specify the description which will be used to describe the feature.
     */
    interface DescriptionOption<T> {
        /**
         * The previously specified assertion container from which we are going to extract the feature.
         */
        val originalAssertionContainer: Expect<T>

        /**
         * Uses [coreFactory].[newMethodCallFormatter][CoreFactory.newMethodCallFormatter] to create a description
         * of a method call with the given [methodName] and the given [arguments].
         */
        fun methodCall(methodName: String, vararg arguments: Any?): RepresentationInCaseOfFailureOption<T> =
            feature(coreFactory.newMethodCallFormatter().format(methodName, arguments))

        /**
         * Uses the given [featureRepresentation] as description.
         */
        fun feature(featureRepresentation: () -> String): RepresentationInCaseOfFailureOption<T> =
            withDescription(Untranslatable(featureRepresentation))

        /**
         * Uses the given [translatable] as description of the feature.
         */
        fun withDescription(translatable: Translatable): RepresentationInCaseOfFailureOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>
            ): DescriptionOption<T> = DescriptionOptionImpl(originalAssertionContainer)
        }
    }

    /**
     * Option step which allows to to define the representation which shall be used in case
     * the extraction cannot be performed.
     */
    interface RepresentationInCaseOfFailureOption<T> {
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
        fun withRepresentationForFailure(translatable: Translatable): CheckOption<T> =
            withRepresentationForFailure(RawString.create(translatable))

        /**
         * Uses the given [representationProvider] to get the representation
         * which will be used in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(representationProvider: () -> Any?): CheckOption<T> =
            withRepresentationForFailure(LazyRepresentation(representationProvider))

        /**
         * Uses the given [representation] in case the extraction cannot be performed.
         */
        fun withRepresentationForFailure(representation: Any): CheckOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable
            ): RepresentationInCaseOfFailureOption<T> =
                RepresentationInCaseOfFailureOptionImpl(originalAssertionContainer, description)
        }
    }

    /**
     *  Option step which allows to specify checks which should be consulted to see whether the feature extraction is
     *  feasible or not.
     */
    interface CheckOption<T> {
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
         * Defines whether the feature can be extracted or not.
         */
        fun withCheck(canBeTransformed: (T) -> Boolean): FeatureExtractionOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable,
                representationForFailure: Any
            ): CheckOption<T> = CheckOptionImpl(originalAssertionContainer, description, representationForFailure)
        }
    }

    /**
     * Option step to define the feature extraction as such.
     */
    interface FeatureExtractionOption<T> {
        /**
         * The so far chosen options up to but not inclusive the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can extract the feature or not.
         */
        val canBeExtracted: (T) -> Boolean

        /**
         * Defines the feature extraction as such which is most likely based on the current subject
         * (but does not need to be).
         */
        fun <R> withFeatureExtraction(extraction: (T) -> R): SubAssertionOption<T, R>

        companion object {
            fun <T> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean
            ): FeatureExtractionOption<T> = FeatureExtractionOptionImpl(checkOption, canBeTransformed)
        }
    }

    /**
     *  Option step which allows to specify sub assertions for the feature (the new subject) and are applied as an
     *  [AssertionGroup].
     */
    interface SubAssertionOption<T, R> {
        /**
         * The so far chosen options up to the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can extract the feature or not.
         */
        val canBeExtracted: (T) -> Boolean

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (T) -> R

        /**
         * In case [assertionCreator] is `null` it is [withoutSubAssertions] otherwise [withSubAssertions].
         */
        fun maybeWithSubAssertions(assertionCreator: (Expect<R>.() -> Unit)?): RepresentationOption<T, R> =
            if (assertionCreator != null) withSubAssertions(assertionCreator)
            else withoutSubAssertions()

        /**
         * Perform the extraction without providing subsequent assertions for the feature (the new subject).
         *
         * We recommend using [withSubAssertions] whenever you have sub assertions as they will be reflected in
         * reporting in case the feature extraction cannot be carried out.
         */
        fun withoutSubAssertions(): RepresentationOption<T, R>

        /**
         * Defines sub assertions for the new subject (after the feature extraction).
         *
         * In contrast to [withoutSubAssertions] we try to reflect the sub assertions in reporting. For instance
         * ```
         * expect(listOf()).first { isLessThan(1) }
         * ```
         * Will result in an error where the reporting will be along the line of
         * ```
         * expect: null
         * - is less than: 1
         *   >> transformation to type Int failed
         * ```
         */
        fun withSubAssertions(assertionCreator: Expect<R>.() -> Unit): RepresentationOption<T, R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R
            ): SubAssertionOption<T, R> = SubAssertionOptionImpl(checkOption, canBeTransformed, transformation)
        }
    }

    /**
     *  Option step which allows to specify a custom representation instead of the feature as such.
     */
    interface RepresentationOption<T, R>{
        /**
         * The so far chosen options up to the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can extract the feature or not.
         */
        val canBeExtracted: (T) -> Boolean

        /**
         * The previously specified feature extraction lambda.
         */
        val featureExtraction: (T) -> R

        /**
         * The previously specified assertionCreator lambda or `null` in case no sub-assertions shall be applied
         * immediately.
         */
        val subAssertions: (Expect<R>.() -> Unit)?

        /**
         * Uses the given [representation] to represent the feature instead of using the feature itself.
         *
         * Use [build] if you do not want to provide a custom represetation.
         */
        fun withRepresentationInsteadOfFeature(representation: Any): FinalStep<T, R>

        /**
         * Skips the option of defining a custom representation (uses the feature as such) and
         * finishes the `feature extraction`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return The newly created [Expect].
         */
        fun build(): Expect<R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R,
                subAssertions: (Expect<R>.() -> Unit)?
            ): RepresentationOption<T, R> = RepresentationOptionImpl(checkOption, canBeTransformed, transformation, subAssertions)
        }
    }

    interface FinalStep<T, R> {
        /**
         * The so far chosen options up to the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can transform the current subject
         * to the new one or not.
         */
        val canBeExtracted: (T) -> Boolean

        /**
         * The previously specified new subject.
         */
        val featureExtraction: (T) -> R

        /**
         * The previously specified assertionCreator lambda or `null` in case no sub-assertions shall be applied
         * immediately.
         */
        val subAssertions: (Expect<R>.() -> Unit)?

        /**
         * The previously specified representation which shall be used instead of the future as such -- `null` means
         * use the feature as such.
         */
        val representationInsteadOfFeature: Any?

        /**
         * Finishes the `feature extraction`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return The newly created [Expect].
         */
        fun build(): Expect<R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R,
                subAssertions: (Expect<R>.() -> Unit)?,
                representationInsteadOfFeature: Any?
            ): FinalStep<T, R> = FinalStepImpl(checkOption, canBeTransformed, transformation, subAssertions, representationInsteadOfFeature)
        }
    }
}
