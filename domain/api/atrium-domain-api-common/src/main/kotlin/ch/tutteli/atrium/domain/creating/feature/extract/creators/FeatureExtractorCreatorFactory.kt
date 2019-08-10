@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.creating.feature.extract.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [FeatureExtractorCreatorFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Switch from `Assert` to `Expect` and use ch.tutteli.atrium.domain.creating.changers.featureExtractor instead; will be removed with 1.0.0")
val featureExtractorCreatorFactory by lazy { loadSingleService(FeatureExtractorCreatorFactory::class) }

/**
 * Responsible to create a [FeatureExtractor.Creator].
 */
@Deprecated("Switch from `Assert` to `Expect` and use ch.tutteli.atrium.domain.creating.changers.FeatureExtractor instead; will be removed with 1.0.0")
interface FeatureExtractorCreatorFactory {

    /**
     * Creates a [FeatureExtractor.Creator] based on the given [featureRepresentation] and [parameterObject]
     *
     * @param featureRepresentation used as [AssertionGroup.description].
     * @param parameterObject Parameter object which contains inter alia the
     *   [FeatureExtractor.ParameterObject.canBeExtracted] and [FeatureExtractor.ParameterObject.featureExtraction]
     *   functions.
     *
     * @return The newly created feature extractor creator.
     */
    fun <TSubject: Any, T: Any> create(
        featureRepresentation: Translatable,
        parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
    ): FeatureExtractor.Creator<TSubject, T>

    /**
     * Creates a [FeatureExtractor.CreatorNullable] based on the given [featureRepresentation] and [parameterObject]
     *
     * @param featureRepresentation used as [AssertionGroup.description].
     * @param parameterObject Parameter object which contains inter alia the
     *   [FeatureExtractor.ParameterObject.canBeExtracted] and [FeatureExtractor.ParameterObject.featureExtraction]
     *   functions.
     *
     * @return The newly created feature extractor creator.
     */
    fun <TSubject: Any, T> createNullable(
        featureRepresentation: Translatable,
        parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
    ): FeatureExtractor.CreatorNullable<TSubject, T>
}
