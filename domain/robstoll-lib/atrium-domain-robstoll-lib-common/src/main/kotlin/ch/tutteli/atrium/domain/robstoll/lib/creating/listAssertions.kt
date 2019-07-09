package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.translations.DescriptionListAssertion

fun <E, T : List<E>> _get(
    assertionContainer: Expect<T>,
    index: Int
): Expect<E> = get(assertionContainer, index, null)

fun <E, T : List<E>> _get(
    assertionContainer: Expect<T>,
    index: Int,
    assertionCreator: Expect<E>.() -> Unit
): Assertion = ExpectImpl.collector.collect(assertionContainer) {
    get(this, index, assertionCreator)
}

private fun <E, T : List<E>> get(
    assertionContainer: Expect<T>,
    index: Int,
    assertionCreator: (Expect<E>.() -> Unit)?
): Expect<E> = ExpectImpl.feature.extractor(assertionContainer)
    .methodCall("get", index)
    .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
    .withCheck { index < it.size }
    .withFeatureExtraction { it[index] }
    .maybeWithSubAssertions(assertionCreator)
    .build()


fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int
): AssertionPlant<T> = extractorForGetCall(index)
    .withParameterObject(createGetParameterObject(plant, index))
    .extract()

fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): Assertion = extractorForGetCall(index)
    .withParameterObject(createGetParameterObject(plant, index))
    .extractAndAssertIt(assertionCreator)

fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int
): AssertionPlantNullable<T> = extractorForGetCall(index)
    .withParameterObjectNullable(createGetParameterObject(plant, index))
    .extract()

fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): Assertion = extractorForGetCall(index)
    .withParameterObjectNullable(createGetParameterObject(plant, index))
    .extractAndAssertIt(assertionCreator)

private fun extractorForGetCall(index: Int) = AssertImpl.feature.extractor.methodCall("get", index)

private fun <T> createGetParameterObject(
    plant: AssertionPlant<List<T>>,
    index: Int
): FeatureExtractor.ParameterObject<List<T>, T> = FeatureExtractor.ParameterObject(
    plant,
    extractionNotSuccessful = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS,
    warningCannotEvaluate = DescriptionListAssertion.CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS,
    canBeExtracted = { index < it.size },
    featureExtraction = { it[index] }
)
