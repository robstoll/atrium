package ch.tutteli.atrium.domain.creating.feature.extract.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor.ParameterObject
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [FeatureExtractorCreatorFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val featureExtractorCreatorFactory by lazy { loadSingleService(FeatureExtractorCreatorFactory::class) }

/**
 * Responsible to create a [FeatureExtractor.Creator].
 */
interface FeatureExtractorCreatorFactory {

    /**
     * Creates a [FeatureExtractor.Creator] based on the given [featureRepresentation], [parameterObject] and
     * [collectingPlantFactory].
     *
     * @param featureRepresentation used as [AssertionGroup.description].
     * @param parameterObject Parameter object which contains inter alia the [ParameterObject.canBeExtracted] and
     *   [ParameterObject.featureExtraction] functions.
     * @param collectingPlantFactory The factory method which creates the appropriate collecting plant which is suitable
     *   for the given `assertionCreator` argument when calling [FeatureExtractor.Creator.extractAndAssertIt].
     */
    fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> create(
        featureRepresentation: Translatable,
        parameterObject: FeatureExtractor.ParameterObject<T>,
        collectingPlantFactory: (() -> T) -> C
    ): FeatureExtractor.Creator<T, A, C>
}
