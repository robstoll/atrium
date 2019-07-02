package ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.assertions.builders.partiallyFixedClaimGroup
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable


abstract class BaseFeatureExtractorCreator<TSubject, T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>>(
    override val featureRepresentation: Translatable,
    override val parameterObject: FeatureExtractor.ParameterObject<TSubject, T>,
    private val plantCreator: (AssertionPlantWithCommonFields.CommonFields<T>) -> A,
    private val collectingPlantFactory: (() -> T) -> C
) : FeatureExtractor.CreatorLike<TSubject, T, A, C> {

    override fun extract(): A {
        val isSafeToExtract = safeToExtract()
        val featureExtractionOnce = parameterObject.featureExtraction.evalOnce()
        return if(isSafeToExtract){
             plantCreator(AssertionPlantWithCommonFields.CommonFields(
                 featureRepresentation,
                 featureExtractionOnce,
                 featureExtractionOnce,
                 coreFactory.newFeatureAssertionChecker(parameterObject.subjectPlant),
                 RawString.NULL
             ))
        } else {
            val representationProvider = { RawString.create(parameterObject.extractionNotSuccessful) }
            val plant = plantCreator(AssertionPlantWithCommonFields.CommonFields(
                Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG),
                { throw PlantHasNoSubjectException() },
                representationProvider,
                coreFactory.newDelegatingAssertionChecker(parameterObject.subjectPlant),
                RawString.NULL
            ))

            plant.addAssertion(AssertImpl.builder.fixedClaimGroup
                .withFeatureType
                .failing
                .withDescriptionAndRepresentation(featureRepresentation, representationProvider)
                .withAssertion(AssertImpl.builder.explanatoryGroup
                    .withWarningType
                    .withExplanatoryAssertion(parameterObject.warningCannotEvaluate)
                    .build()
                )
                .build()
            )
        }
    }

    override fun extractAndAssertIt(assertionCreator: C.() -> Unit): Assertion {
        val isSafeToExtract = safeToExtract()
        val featureExtractionOnce = parameterObject.featureExtraction.evalOnce()
        return AssertImpl.builder.partiallyFixedClaimGroup
            .withFeatureType
            .withClaim(isSafeToExtract)
            .withDescriptionAndRepresentation(featureRepresentation) {
                if (isSafeToExtract) featureExtractionOnce() ?: RawString.NULL
                else RawString.create(parameterObject.extractionNotSuccessful)
            }
            .withAssertion(
                AssertImpl.collector.collectOrExplain(
                    isSafeToExtract,
                    parameterObject.warningCannotEvaluate,
                    featureExtractionOnce,
                    collectingPlantFactory,
                    assertionCreator
                ))
            .build()
    }

    private fun safeToExtract(): Boolean {
        //TODO remove try-catch with 1.0.0, should no longer be necessary
        return try {
            parameterObject.canBeExtracted()
        } catch (e: PlantHasNoSubjectException) {
            true //TODO that's kind of a hack, do we have a better solution?
        }
    }
}

class FeatureExtractorCreatorImpl<TSubject: Any, T: Any>(
    featureRepresentation: Translatable,
    parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
) : FeatureExtractor.Creator<TSubject, T>,
    BaseFeatureExtractorCreator<TSubject, T, AssertionPlant<T>, CollectingAssertionPlant<T>>(
        featureRepresentation,
        parameterObject,
        AssertImpl.coreFactory::newReportingPlant,
        AssertImpl.coreFactory::newCollectingPlant
    )

class FeatureExtractorCreatorNullableImpl<TSubject: Any, T>(
    featureRepresentation: Translatable,
    parameterObject: FeatureExtractor.ParameterObject<TSubject, T>
) : FeatureExtractor.CreatorNullable<TSubject, T>,
    BaseFeatureExtractorCreator<TSubject, T, AssertionPlantNullable<T>, CollectingAssertionPlantNullable<T>>(
        featureRepresentation,
        parameterObject,
        AssertImpl.coreFactory::newReportingPlantNullable,
        AssertImpl.coreFactory::newCollectingPlantNullable
    )
