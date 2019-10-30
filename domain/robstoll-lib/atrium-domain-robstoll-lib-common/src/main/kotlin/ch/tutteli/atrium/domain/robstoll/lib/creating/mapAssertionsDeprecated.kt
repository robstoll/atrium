@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.utils.subAssert
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <K, V> _contains(
    plant: AssertionPlant<Map<out K, V>>,
    pairs: List<Pair<K, V>>
): Assertion = _containsKeyWithValueAssertion<K, Any>(plant, pairs.map {
    it.first to it.second?.let { expected -> subAssert<Any> { toBe(expected as Any) } }
})

fun <K, V : Any> _containsKeyWithValueAssertion(
    plant: AssertionPlant<Map<out K, V?>>,
    keyValues: List<Pair<K, (Assert<V>.() -> Unit)?>>
): Assertion = containsNullable(plant, keyValues.map { it }) { assertionCreator ->
    // we can pretend that the subject is null if maybeSubject is None as we have to be in an explaining like context in such a case
    val subjectIsNull = this.maybeSubject.fold({ true }) { it == null }
    if (assertionCreator != null && !subjectIsNull) {
        @Suppress("DEPRECATION" /* TODO switch to Expect */)
        ExpectImpl.changeSubject(this).unreported { it as V }.assertionCreator()
    } else if (subjectIsNull && assertionCreator == null) {
        addAssertion(AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.NULL, trueProvider))
    } else {
        addAssertion(
            AssertImpl.builder.explanatoryGroup
                .withDefaultType
                .withAssertions(
                    AssertImpl.collector.forExplanation.throwIfNoAssertionIsCollected.collect(
                        DescriptionMapAssertion.CANNOT_EVALUATE_KEY_DOES_NOT_EXIST,
                        MaybeSubject.Absent,
                        assertionCreator
                    )
                )
                .build()
        )
    }
}

private fun <K, V : Any, M> containsNullable(
    plant: AssertionPlant<Map<out K, V?>>,
    pairs: List<Pair<K, M>>,
    assertionCreator: AssertionPlantNullable<V?>.(M) -> Unit
) = contains(
    pairs,
    { option, key -> option.withParameterObjectNullable(createGetParameterObject(plant, key)) },
    assertionCreator
)

private fun <K, V, M, A : BaseAssertionPlant<V, A>, C : BaseCollectingAssertionPlant<V, A, C>> contains(
    pairs: List<Pair<K, M>>,
    @Suppress("DEPRECATION") parameterObjectOption: (FeatureExtractor.ParameterObjectOption, K) -> FeatureExtractor.CreatorLike<Map<out K, V>, V, A, C>,
    assertionCreator: C.(M) -> Unit
): Assertion = LazyThreadUnsafeAssertionGroup {
    //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via AssertionPlant
    val methodCallFormatter = AssertImpl.coreFactory.newMethodCallFormatter()
    val assertions = pairs.map { (key: K, matcher: M) ->
        val option = AssertImpl.feature.extractor
            .withDescription(
                TranslatableWithArgs(DescriptionMapAssertion.ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key))
            )
        parameterObjectOption(option, key)
            .extractAndAssertIt { assertionCreator(matcher) }
    }
    AssertImpl.builder.list
        .withDescriptionAndEmptyRepresentation(DescriptionMapAssertion.CONTAINS_IN_ANY_ORDER)
        .withAssertions(assertions)
        .build()
}


fun <K, V : Any> _getExisting(plant: AssertionPlant<Map<out K, V>>, key: K): AssertionPlant<V> =
    extractorForGetCall(key)
        .withParameterObject(createGetParameterObject(plant, key))
        .extract()

fun <K, V : Any> _getExisting(
    plant: AssertionPlant<Map<out K, V>>,
    key: K,
    assertionCreator: CollectingAssertionPlant<V>.() -> Unit
): Assertion = extractorForGetCall(key)
    .withParameterObject(createGetParameterObject(plant, key))
    .extractAndAssertIt(assertionCreator)

fun <K, V> _getExistingNullable(plant: AssertionPlant<Map<out K, V>>, key: K): AssertionPlantNullable<V> =
    extractorForGetCall(key)
        .withParameterObjectNullable(createGetParameterObject(plant, key))
        .extract()

fun <K, V> _getExistingNullable(
    plant: AssertionPlant<Map<out K, V>>,
    key: K,
    assertionCreator: CollectingAssertionPlantNullable<V>.() -> Unit
): Assertion = extractorForGetCall(key)
    .withParameterObjectNullable(createGetParameterObject(plant, key))
    .extractAndAssertIt(assertionCreator)


private fun <K> extractorForGetCall(key: K) = AssertImpl.feature.extractor.methodCall("get", key)

@Suppress("DEPRECATION")
private fun <K, V> createGetParameterObject(
    plant: AssertionPlant<Map<out K, V>>,
    key: K
): FeatureExtractor.ParameterObject<Map<out K, V>, V> = FeatureExtractor.ParameterObject(
    plant,
    extractionNotSuccessful = DescriptionMapAssertion.KEY_DOES_NOT_EXIST,
    warningCannotEvaluate = DescriptionMapAssertion.CANNOT_EVALUATE_KEY_DOES_NOT_EXIST,
    canBeExtracted = { it.containsKey(key) },
    featureExtraction = {
        @Suppress(
            "UNCHECKED_CAST"
            /*
            UNCHECKED_CAST is OK as this function will only be called if the key exists, so the value should be V
            One note though, if one deals with a Map returned by Java code and forgot that the Map actually contains
            `null` as values as well, then we ignore it here (due to the UNCHECKED_CAST). However, usually this
            should not matter as the assertion about the value will cover it. In the worst case, a null-check included
            by the Kotlin compiler will throw -> in such a case it might be hard for the user to grasp what is going on.
            In this sense it might be better if we catch that already here and report accordingly. Yet, in the end we
            end up introducing null-checks everywhere only because of Java => keep it like this for now.
            */
        )
        it[key] as V
    }
)

fun _hasSize(plant: AssertionPlant<Map<*, *>>, size: Int): Assertion = AssertImpl.collector.collect(plant) {
    property(Map<*, *>::size) { toBe(size) }
}

fun <K> _keys(plant: AssertionPlant<Map<out K, *>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map<out K, *>::keys, assertionCreator) }

fun <V> _values(plant: AssertionPlant<Map<*, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
//TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) { property(Map<*, V>::values, assertionCreator) }
