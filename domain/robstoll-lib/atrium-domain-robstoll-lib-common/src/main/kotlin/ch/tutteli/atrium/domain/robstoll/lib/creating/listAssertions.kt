package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.translations.DescriptionListAssertion

fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): Assertion = extractGetCall(index)
    .withParameterObject(createGetParameterObject(plant, index))
    .subAssertions(assertionCreator)

fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): Assertion = extractGetCall(index)
    .withParameterObjectNullable(createGetParameterObject(plant, index))
    .subAssertions(assertionCreator)

private fun extractGetCall(index: Int) = AssertImpl.feature.extractor.method("get", index)

private fun <T> createGetParameterObject(
    plant: AssertionPlant<List<T>>,
    index: Int
): FeatureExtractor.ParameterObject<T> = FeatureExtractor.ParameterObject(
    extractionNotSuccessful = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS,
    warningCannotEvaluate = DescriptionListAssertion.CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS,
    canBeExtracted = { index < plant.subject.size },
    featureExtraction = { plant.subject[index] }
)
