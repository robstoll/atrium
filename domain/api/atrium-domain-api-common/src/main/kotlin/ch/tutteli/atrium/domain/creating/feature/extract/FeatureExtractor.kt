@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.feature.extract

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.creating.feature.extract.creators.featureExtractorCreatorFactory
import ch.tutteli.atrium.domain.creating.feature.extract.impl.DescriptionOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [FeatureAssertions] but differs in the intended usage. [FeatureAssertions] are intended to make
 * assertions about a return value of a method call or a property, regardless if this call/access fails or not.
 * The [FeatureExtractor] on the other hand should be used if it is already known, that the call/access fails depending
 * on given arguments. For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
@Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
interface FeatureExtractor {

    companion object {
        /**
         * Entry point to use the feature extractor.
         */
        val builder: DescriptionOption = DescriptionOptionImpl()
    }

    /**
     * Option step which allows to specify the description which will be used to describe the feature.
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    interface DescriptionOption {
        /**
         * Uses [coreFactory].[newMethodCallFormatter][CoreFactory.newMethodCallFormatter] to create a description
         * of a method call with the given [methodName] and the given [arguments].
         */
        fun methodCall(methodName: String, vararg arguments: Any?): ParameterObjectOption
            = feature(coreFactory.newMethodCallFormatter().format(methodName, arguments))

        /**
         * Uses the given [featureRepresentation] as description.
         */
        fun feature(featureRepresentation: () -> String): ParameterObjectOption
            = withDescription(Untranslatable(featureRepresentation))

        /**
         * Uses the given [translatable] as description of the feature.
         */
        fun withDescription(translatable: Translatable): ParameterObjectOption
    }

    /**
     * Option step to define the [ParameterObject].
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    interface ParameterObjectOption {
        /**
         * The previously chosen feature description.
         */
        val featureDescription: Translatable

        /**
         * Uses the given [parameterObject] where a non-nullable feature is extracted by
         * [ParameterObject.featureExtraction].
         */
        fun <TSubject : Any, T : Any> withParameterObject(
            parameterObject: ParameterObject<TSubject, T>
        ): Creator<TSubject, T>
            = featureExtractorCreatorFactory.create(featureDescription, parameterObject)

        /**
         * Uses the given [parameterObject] where a nullable feature is extracted by
         * [ParameterObject.featureExtraction].
         */
        fun <TSubject : Any, T : Any?> withParameterObjectNullable(
            parameterObject: ParameterObject<TSubject, T>
        ): CreatorNullable<TSubject, T>
            = featureExtractorCreatorFactory.createNullable(featureDescription, parameterObject)
    }

    /**
     * Final step of the sophisticated `safe feature extraction` where one can define [extractAndAssertIt]
     * for the extracted feature or use [extract] to get the assertion plant.
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    interface CreatorLike<TSubject, T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> {
        /**
         * The previously chosen feature representation.
         */
        val featureRepresentation: Translatable

        /**
         * The previously created [ParameterObject].
         */
        val parameterObject: ParameterObject<TSubject, T>

        /**
         * Extracts a feature with the help of the specified
         * [parameterObject].[featureExtraction][ParameterObject.featureExtraction] if it
         * [parameterObject].[canBeExtracted][ParameterObject.canBeExtracted] and returns an assertion plant for it.
         *
         * @returns The newly created feature assertion plant.
         */
        fun extract(): A

        /**
         * Extracts a feature with the help of the specified
         * [parameterObject].[featureExtraction][ParameterObject.featureExtraction] if it
         * [parameterObject].[canBeExtracted][ParameterObject.canBeExtracted] and uses the given [assertionCreator]
         * to create feature assertions.
         *
         * In detail, it creates an [AssertionGroup] of type [FeatureAssertionGroupType] and either uses the given
         * [assertionCreator] to create assertions about the feature if
         * [parameterObject].[canBeExtracted][ParameterObject.canBeExtracted] evaluates to `true` or
         * [assertionCreator] is used to create explanatory assertions
         * (in case [parameterObject].[canBeExtracted][ParameterObject.canBeExtracted] evaluates to `false`).
         *
         * Notice, if [PlantHasNoSubjectException] is thrown during the evaluation of
         * [parameterObject].[canBeExtracted][ParameterObject.canBeExtracted],
         * then it is assumed reporting is taken place and it is already in the process of collecting assertions for
         * explanation. In such a case [assertionCreator] is used to create assertions (not explanatory
         * assertions within explanatory assertions).
         *
         * @param assertionCreator A lambda which creates the [Assertion]s for the extracted feature.
         *
         * @returns The assertion representing the feature extraction.
         */
        fun extractAndAssertIt(assertionCreator: C.() -> Unit): Assertion
    }

    /**
     * Final step of the sophisticated `safe feature extraction` where one can define [extractAndAssertIt]
     * for the extracted feature or use [extract] to get a feature [AssertionPlant].
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    interface Creator<TSubject, T : Any> :
        CreatorLike<TSubject, T, AssertionPlant<T>, CollectingAssertionPlant<T>>

    /**
     * Final step of the sophisticated `safe feature extraction` where one can define [extractAndAssertIt]
     * for the extracted feature or use [extract] to get a feature [AssertionPlantNullable].
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    interface CreatorNullable<TSubject, T> :
        CreatorLike<TSubject, T, AssertionPlantNullable<T>, CollectingAssertionPlantNullable<T>>

    /**
     * A parameter object which contains all necessary information to extract a feature -- however, not to create
     * assertions.
     *
     * @param subjectPlant The [AssertionPlant] which contains the subject from which the subject is extracted.
     * @param extractionNotSuccessful Used as [AssertionGroup.representation] in case [canBeExtracted]
     *   evaluates to false.
     * @param warningCannotEvaluate The [Translatable] used to explain why the extraction could not be carried out.

     * @param canBeExtracted Indicates whether it is safe to extract the feature or not (e.g. [Map.containsKey] as
     *   counter part to [Map.get])
     * @param featureExtraction The feature extraction as such (e.g. [Map.get], [List.get] etc.)
     */
    @Deprecated("Switch from `Assert` to `Expect` and use then FeatureExtractor from package ch.tutteli.atrium.domain.creating.changers instead; will be removed with 1.0.0")
    data class ParameterObject<TSubject, T>(
        val subjectPlant: BaseAssertionPlant<TSubject, *>,
        val extractionNotSuccessful: Translatable,
        val warningCannotEvaluate: Translatable,
        val canBeExtracted: (TSubject) -> Boolean,
        val featureExtraction: (TSubject) -> T
    )
}
