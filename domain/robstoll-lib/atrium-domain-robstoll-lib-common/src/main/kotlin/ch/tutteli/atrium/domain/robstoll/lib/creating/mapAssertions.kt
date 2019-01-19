package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import kotlin.reflect.KClass

fun <K, V : Any> _contains(plant: AssertionPlant<Map<K, V>>, pairs: List<Pair<K, V>>): Assertion
    = containsNonNullable(plant, pairs) { value -> toBe(value) }

fun <K, V : Any> _containsNullable(
    plant: AssertionPlant<Map<K, V?>>,
    type: KClass<V>,
    pairs: List<Pair<K, V?>>
): Assertion = containsNullable(plant, pairs) { value ->
    //TODO add toBe(Any?) to AssertionPlantNullable?
    if (value == null) toBe(null)
    else AssertImpl.any.typeTransformation.isNotNull(this, type) { toBe(value) }
}

fun <K, V : Any> _containsKeyWithValueAssertion(
    plant: AssertionPlant<Map<K, V>>,
    keyValues: List<Pair<K, Assert<V>.() -> Unit>>
): Assertion = containsNonNullable(plant, keyValues.map { it }) { assertionCreator -> assertionCreator() }

fun <K, V : Any> _containsKeyWithNullableValueAssertions(
    plant: AssertionPlant<Map<K, V?>>,
    type: KClass<V>,
    keyValues: List<Pair<K, (Assert<V>.() -> Unit)?>>
): Assertion = containsNullable(plant, keyValues.map{ it }) { assertionCreator ->
    //TODO add this functionality to AssertionPlantNullable?
    if(assertionCreator == null) toBe(null)
    else AssertImpl.any.typeTransformation.isNotNull(this, type ){ assertionCreator() }
}

private fun <K, V : Any, M> containsNonNullable(
    plant: AssertionPlant<Map<K, V>>,
    pairs: List<Pair<K, M>>,
    assertionCreator: AssertionPlant<V>.(M) -> Unit
) = contains(
    pairs,
    { option, key -> option.withParameterObject(createGetParameterObject(plant, key)) },
    assertionCreator
)

private fun <K, V : Any, M> containsNullable(
    plant: AssertionPlant<Map<K, V?>>,
    pairs: List<Pair<K, M>>,
    assertionCreator: AssertionPlantNullable<V?>.(M) -> Unit
) = contains(
    pairs,
    { option, key -> option.withParameterObjectNullable(createGetParameterObject(plant, key)) },
    assertionCreator
)

private  fun <K, V, M, A : BaseAssertionPlant<V, A>, C : BaseCollectingAssertionPlant<V, A, C>> contains(
    pairs: List<Pair<K, M>>,
    parameterObjectOption: (FeatureExtractor.ParameterObjectOption, K) -> FeatureExtractor.Creator<V, A, C>,
    assertionCreator: C.(M) -> Unit
): Assertion =  LazyThreadUnsafeAssertionGroup {
    //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via AssertionPlant
    val methodCallFormatter = AssertImpl.coreFactory.newMethodCallFormatter()
    val assertions = pairs.map { (key: K, matcher: M) ->
        val option = AssertImpl.feature.extractor
            .withDescription(
                TranslatableWithArgs(DescriptionMapAssertion.ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key))
            )
        parameterObjectOption(option, key)
            .extractAndAssertIt{ assertionCreator(matcher) }
    }
    AssertImpl.builder.list
        .withDescriptionAndEmptyRepresentation(DescriptionMapAssertion.CONTAINS_IN_ANY_ORDER)
        .withAssertions(assertions)
        .build()
}


fun <K> _containsKey(plant: AssertionPlant<Map<K, *>>, key: K): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionMapAssertion.CONTAINS_KEY, key) { plant.subject.containsKey(key) }

fun <K> _containsNotKey(plant: AssertionPlant<Map<K, *>>, key: K): Assertion
    = AssertImpl.builder.createDescriptive(DescriptionMapAssertion.CONTAINS_NOT_KEY, key) { plant.subject.containsKey(key).not()  }

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

fun <V> _values(plant: AssertionPlant<Map<*, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map<*, V>::values, assertionCreator) }

