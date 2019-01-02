package ch.tutteli.atrium.domain.creating.feature.extract

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.creating.feature.extract.creators.featureExtractorCreatorFactory
import ch.tutteli.atrium.domain.creating.feature.extract.impl.RepresentationOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable


/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [FeatureAssertions] but differs in the intended usage. [FeatureAssertions] are intended to make
 * assertions about a return value of a method call or a property, regardless if this call/access fails or not.
 * The [FeatureExtractor] on the other hand should be used if it is already known, that the call/access fails depending
 * on given arguments. For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
interface FeatureExtractor {

    companion object {
        /**
         * Entry point to use the feature extractor.
         */
        val builder: RepresentationOption =
            RepresentationOptionImpl()
    }

    /**
     * Provides options to chose the representation of the feature.
     */
    interface RepresentationOption {
        /**
         * Uses [coreFactory].[newMethodCallFormatter][CoreFactory.newMethodCallFormatter] to create a representation
         * of a method call with the given [methodName] and the given [arguments].
         */
        fun method(methodName: String, vararg arguments: Any?): ParameterObjectOption
            = feature(coreFactory.newMethodCallFormatter().format(methodName, arguments))

        /**
         * Uses the given [featureRepresentation] as representation.
         */
        fun feature(featureRepresentation: () -> String): ParameterObjectOption
    }

    /**
     * Step to define the [ParameterObject].
     */
    interface ParameterObjectOption {
        /**
         * The previously chosen feature representation.
         */
        val featureRepresentation: () -> String

        /**
         * Uses the given [parameterObject] where a non-nullable feature is extracted by
         * [ParameterObject.featureExtraction].
         */
        fun <T : Any> withParameterObject(
            parameterObject: ParameterObject<T>
        ): Creator<T, Assert<T>, CollectingAssertionPlant<T>> {
            return featureExtractorCreatorFactory.create(
                featureRepresentation, parameterObject, coreFactory::newCollectingPlant
            )
        }

        /**
         * Uses the given [parameterObject] where a nullable feature is extracted by
         * [ParameterObject.featureExtraction].
         */
        fun <T : Any?> withParameterObjectNullable(
            parameterObject: ParameterObject<T>
        ): Creator<T, AssertionPlantNullable<T>, CollectingAssertionPlantNullable<T>> {
            return featureExtractorCreatorFactory.create(
                featureRepresentation, parameterObject, coreFactory::newCollectingPlantNullable
            )
        }
    }

    /**
     * Final step of the sophisticated `safe feature extraction` where once can define [subAssertions] for the extracted
     * feature.
     */
    interface Creator<T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>>  {
        /**
         * The previously chosen feature representation.
         */
        val featureRepresentation: () -> String

        /**
         * The previously created [ParameterObject].
         */
        val parameterObject: ParameterObject<T>

        /**
         * The factory method which creates the appropriate collecting plant which is suitable
         * for the given `assertionCreator` argument when calling [subAssertions].
         */
        val collectingPlantFactory: (() -> T) -> C

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
         */
        fun subAssertions(assertionCreator: C.() -> Unit): Assertion
    }

    /**
     * A parameter object which contains all necessary information to extract a feature -- however, not to create
     * assertions.
     *
     * @param extractionNotSuccessful Used as [AssertionGroup.representation] in case [canBeExtracted]
     *   evaluates to false.
     * @param warningCannotEvaluate The [Translatable] used to explain why the extraction could not be carried out.

     * @param canBeExtracted Indicates whether it is safe to extract the feature or not (e.g. [Map.containsKey] as
     *   counter part to [Map.get])
     * @param featureExtraction The feature extraction as such (e.g. [Map.get], [List.get] etc.)
     */
    data class ParameterObject<T>(
        val extractionNotSuccessful: Translatable,
        val warningCannotEvaluate: Translatable,
        val canBeExtracted: () -> Boolean,
        val featureExtraction: () -> T
    )
}
