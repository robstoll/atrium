package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion

fun <K, V : Any> _contains(plant: AssertionPlant<Map<K, V>>, pairs: List<Pair<K, V>>): Assertion {
    return LazyThreadUnsafeAssertionGroup {
        //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via AssertionPlant
        val methodCallFormatter = AssertImpl.coreFactory.newMethodCallFormatter()
        val assertions = pairs.map { (key: K, value: V) ->
            AssertImpl.feature.extractor
                .withDescription(
                    TranslatableWithArgs(DescriptionMapAssertion.ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key))
                )
                .withParameterObject(createGetParameterObject(plant, key))
                .extractAndAssertIt { toBe(value) }
        }
        AssertImpl.builder.list
            .withDescriptionAndEmptyRepresentation(DescriptionMapAssertion.CONTAINS_IN_ANY_ORDER)
            .withAssertions(assertions)
            .build()
    }

}

fun <K> _containsKey(plant: AssertionPlant<Map<K, *>>, key: K): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionMapAssertion.CONTAINS_KEY, key) { plant.subject.containsKey(key) }

fun <K, V : Any> _getExisting(
    plant: AssertionPlant<Map<K, V>>,
    key: K,
    assertionCreator: CollectingAssertionPlant<V>.() -> Unit
): Assertion = extractorForGetCall(key)
    .withParameterObject(createGetParameterObject(plant, key))
    .extractAndAssertIt(assertionCreator)

fun <K, V> _getExistingNullable(
    plant: AssertionPlant<Map<K, V>>,
    key: K,
    assertionCreator: CollectingAssertionPlantNullable<V>.() -> Unit
): Assertion = extractorForGetCall(key)
    .withParameterObjectNullable(createGetParameterObject(plant, key))
    .extractAndAssertIt(assertionCreator)

private fun <K> extractorForGetCall(key: K) = AssertImpl.feature.extractor.methodCall("get", key)

private fun <K, V> createGetParameterObject(
    plant: AssertionPlant<Map<K, V>>,
    key: K
): FeatureExtractor.ParameterObject<V> = FeatureExtractor.ParameterObject(
    extractionNotSuccessful = DescriptionMapAssertion.KEY_DOES_NOT_EXIST,
    warningCannotEvaluate = DescriptionMapAssertion.CANNOT_EVALUATE_KEY_DOES_NOT_EXIST,
    canBeExtracted = { plant.subject.containsKey(key) },
    featureExtraction = {
        @Suppress("UNCHECKED_CAST" /* that's fine will only be called if the key exists */)
        plant.subject[key] as V
    }
)

fun _hasSize(plant: AssertionPlant<Map<*, *>>, size: Int): Assertion = AssertImpl.collector.collect(plant) {
    property(Map<*, *>::size) { toBe(size) }
}

fun _isEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.create(EMPTY)) { plant.subject.isEmpty() }

fun _isNotEmpty(plant: AssertionPlant<Map<*, *>>): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionBasic.IS_NOT, RawString.create(EMPTY)) { plant.subject.isNotEmpty() }

fun <K> _keys(plant: AssertionPlant<Map<K, *>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map<K, *>::keys, assertionCreator) }

fun <V> _values(
    plant: AssertionPlant<Map<*, V>>,
    assertionCreator: AssertionPlant<Collection<V>>.() -> Unit
): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map<*, V>::values, assertionCreator) }

