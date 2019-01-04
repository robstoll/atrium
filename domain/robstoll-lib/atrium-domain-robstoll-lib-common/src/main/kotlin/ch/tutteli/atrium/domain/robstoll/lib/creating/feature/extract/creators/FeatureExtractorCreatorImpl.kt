package ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.partiallyFixedClaimGroup
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

class FeatureExtractorCreatorImpl<T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>>(
    override val featureRepresentation: Translatable,
    override val parameterObject: FeatureExtractor.ParameterObject<T>,
    override val collectingPlantFactory: (() -> T) -> C
) : FeatureExtractor.Creator<T, A, C> {

    override fun extractAndAssertIt(assertionCreator: C.() -> Unit): Assertion {
        val safeToExtract = try {
            parameterObject.canBeExtracted()
        } catch (e: PlantHasNoSubjectException) {
            true //TODO that's kind of a hack, do we have a better solution?
        }

        val featureExtractionOnce = parameterObject.featureExtraction.evalOnce()
        return AssertImpl.builder.partiallyFixedClaimGroup
            .withFeatureType
            .withClaim(safeToExtract)
            .withDescriptionAndRepresentation(featureRepresentation) {
                if (safeToExtract) featureExtractionOnce() ?: RawString.NULL
                else RawString.create(parameterObject.extractionNotSuccessful)
            }
            .withAssertion(
                AssertImpl.collector.collectOrExplain(
                    safeToExtract,
                    parameterObject.warningCannotEvaluate,
                    featureExtractionOnce,
                    collectingPlantFactory,
                    assertionCreator
                ))
            .build()
    }
}
