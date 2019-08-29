@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating.feature.extract.creators

import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.creating.feature.extract.creators.FeatureExtractorCreatorFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators.FeatureExtractorCreatorImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators.FeatureExtractorCreatorNullableImpl
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("use FeatureExtractorImpl instead; will be removed with 1.0.0")
class FeatureExtractorCreatorFactoryImpl : FeatureExtractorCreatorFactory {

    override fun <TSubject : Any, T : Any> create(
        featureRepresentation: Translatable,
        parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
    ): FeatureExtractor.Creator<TSubject, T> = FeatureExtractorCreatorImpl(featureRepresentation, parameterObject)

    override fun <TSubject : Any, T> createNullable(
        featureRepresentation: Translatable,
        parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
    ): FeatureExtractor.CreatorNullable<TSubject, T> =
        FeatureExtractorCreatorNullableImpl(featureRepresentation, parameterObject)
}


