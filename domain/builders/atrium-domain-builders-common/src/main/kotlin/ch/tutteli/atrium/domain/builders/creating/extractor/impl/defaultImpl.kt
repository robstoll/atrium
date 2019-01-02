package ch.tutteli.atrium.domain.builders.creating.extractor.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.partiallyFixedClaimGroup
import ch.tutteli.atrium.domain.builders.creating.collectors.collectOrExplain
import ch.tutteli.atrium.domain.builders.creating.extractor.FeatureExtractor
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable

class RepresentationOptionImpl : FeatureExtractor.RepresentationOption {
    override fun feature(featureRepresentation: () -> String): FeatureExtractor.ParameterObjectOption
        = ParameterObjectOptionImpl(featureRepresentation)
}

class ParameterObjectOptionImpl(
    private val featureRepresentation: () -> String
) : FeatureExtractor.ParameterObjectOption {

    override fun <T : Any> withParameterObject(
        parameterObject: FeatureExtractor.ParameterObject<T>
    ): FeatureExtractor.Creator<T, Assert<T>, CollectingAssertionPlant<T>>
        = withGenericParameterObject(parameterObject, coreFactory::newCollectingPlant)

    override fun <T> withParameterObjectNullable(
        parameterObject: FeatureExtractor.ParameterObject<T>
    ): FeatureExtractor.Creator<T, AssertionPlantNullable<T>, CollectingAssertionPlantNullable<T>>
        = withGenericParameterObject(parameterObject, coreFactory::newCollectingPlantNullable)

    private fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> withGenericParameterObject(
        parameterObject: FeatureExtractor.ParameterObject<T>,
        collectingPlantFactory: (() -> T) -> C
    ): FeatureExtractor.Creator<T, A, C> = CreatorImpl(featureRepresentation, parameterObject, collectingPlantFactory)
}

class CreatorImpl<T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>>(
    override val featureRepresentation: () -> String,
    override val parameterObject: FeatureExtractor.ParameterObject<T>,
    override val collectingPlantFactory: (() -> T) -> C
) : FeatureExtractor.Creator<T, A, C> {

    override fun subAssertions(assertionCreator: C.() -> Unit): Assertion {
        val safeToExtract = try {
            parameterObject.canBeExtracted()
        } catch (e: PlantHasNoSubjectException) {
            true //TODO that's kind of a hack, do we have a better solution?
        }

        val featureExtractionOnce = parameterObject.featureExtraction.evalOnce()
        return AssertImpl.builder.partiallyFixedClaimGroup
            .withFeatureType
            .withClaim(safeToExtract)
            .withDescriptionAndRepresentation(Untranslatable(featureRepresentation)) {
                if (safeToExtract) featureExtractionOnce() ?: RawString.NULL
                else RawString.create(parameterObject.extractionNotSuccessful)
            }
            .withAssertion(AssertImpl.collector.collectOrExplain(
                safeToExtract,
                parameterObject.warningCannotEvaluate,
                featureExtractionOnce,
                collectingPlantFactory,
                assertionCreator
            ))
            .build()
    }
}
