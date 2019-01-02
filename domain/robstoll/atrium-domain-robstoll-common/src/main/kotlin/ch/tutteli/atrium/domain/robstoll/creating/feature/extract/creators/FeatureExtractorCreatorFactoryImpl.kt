package ch.tutteli.atrium.domain.robstoll.creating.feature.extract.creators

import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.creating.feature.extract.creators.FeatureExtractorCreatorFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators.FeatureExtractorCreatorImpl

class FeatureExtractorCreatorFactoryImpl: FeatureExtractorCreatorFactory {

    override fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> create(
        featureRepresentation: () -> String,
        parameterObject: FeatureExtractor.ParameterObject<T>,
        collectingPlantFactory: (() -> T) -> C
    ): FeatureExtractor.Creator<T, A, C> = FeatureExtractorCreatorImpl(
        featureRepresentation, parameterObject, collectingPlantFactory
    )
}


