@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.partiallyFixedClaimGroup
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("use _extractFeature instead; will be removed with 1.0.0")
abstract class BaseFeatureExtractorCreator<TSubject, T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>>(
    override val featureRepresentation: Translatable,
    override val parameterObject: FeatureExtractor.ParameterObject<TSubject, T>,
    private val plantCreator: (AssertionPlantWithCommonFields.CommonFields<T>) -> A,
    private val collectingPlantFactory: (() -> T) -> C
) : FeatureExtractor.CreatorLike<TSubject, T, A, C> {

    override fun extract(): A {

        val isSafeToExtract = safeToExtract()
        return if(isSafeToExtract) {
            val featureExtractionOnce = featureExtractionOnce()
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
                SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
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

    private fun featureExtractionOnce(): () -> T {
        return {
            val subject by lazy {
                @Suppress("DEPRECATION")
                parameterObject.featureExtraction(parameterObject.subjectPlant.subject)
            }
            subject
        }
    }

    override fun extractAndAssertIt(assertionCreator: C.() -> Unit): Assertion {
        val isSafeToExtract = safeToExtract()
        val featureExtractionOnce = featureExtractionOnce()
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

    private fun safeToExtract(): Boolean  =
        parameterObject.subjectPlant.maybeSubject.fold(falseProvider, parameterObject.canBeExtracted)
}

@Suppress("DEPRECATION")
@Deprecated("use _extractFeature instead; will be removed with 1.0.0")
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

@Suppress("DEPRECATION")
@Deprecated("use _extractFeature instead; will be removed with 1.0.0")
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
