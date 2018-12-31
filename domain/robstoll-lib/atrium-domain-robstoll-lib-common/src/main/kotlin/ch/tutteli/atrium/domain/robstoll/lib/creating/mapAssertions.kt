package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectOrExplain
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion

fun <T : Map<*, *>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = AssertImpl.collector.collect(plant) {
        property(Map<*, *>::size) { toBe(size) }
    }

fun <T : Map<*, *>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isEmpty() }
        .withDescriptionAndRepresentation(DescriptionBasic.IS, RawString.create(DescriptionCollectionAssertion.EMPTY))
        .build()

fun <T : Map<*, *>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isNotEmpty() }
        .withDescriptionAndRepresentation(DescriptionBasic.IS_NOT, RawString.create(DescriptionCollectionAssertion.EMPTY))
        .build()

fun <K, V> _keys(plant: AssertionPlant<Map<K, V>>, assertionCreator: AssertionPlant<Set<K>>.() -> Unit): Assertion
    //TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) {
        property(Map<K, V>::keys, assertionCreator)
    }

fun <K, V> _values(plant: AssertionPlant<Map<K, V>>, assertionCreator: AssertionPlant<Collection<V>>.() -> Unit): Assertion
    //TODO check that one assertion was created - problem property creates at least a feature assertion group, that's why collect is happy
    = AssertImpl.collector.collect(plant) {
        property(Map<K, V>::values, assertionCreator)
    }

fun <K, V : Any, T : Map<K, V>> _getExisting(
    plant: AssertionPlant<T>,
    key: K,
    assertionCreator: CollectingAssertionPlant<V>.() -> Unit
): Assertion = getExisting(plant, key, coreFactory::newCollectingPlant, assertionCreator)


fun <K, V, T : Map<K, V>> _getExistingNullable(
    plant: AssertionPlant<T>,
    key: K,
    assertionCreator: CollectingAssertionPlantNullable<V>.() -> Unit
) : Assertion = getExisting(plant, key, coreFactory::newCollectingPlantNullable, assertionCreator)

private fun <K, V, T : Map<K, V>, A : BaseAssertionPlant<V, A>, C : BaseCollectingAssertionPlant<V, A, C>> getExisting(
    plant: AssertionPlant<T>,
    key: K,
    collectingPlantFactory: (() -> V) -> C,
    assertionCreator: C.() -> Unit
): Assertion {
    val holds = try {
        plant.subject.containsKey(key)
    } catch (e: PlantHasNoSubjectException) {
        true //TODO that's a hack, do we have a better solution?
    }

    val assertion: AssertionGroup = AssertImpl.collector.collectOrExplain(
        holds,
        DescriptionMapAssertion.CANNOT_EVALUATE_KEY_DOES_NOT_EXIST,
        {
            @Suppress("UNCHECKED_CAST" /* that's fine will only be called if the key exists */)
            plant.subject[key] as V
        },
        collectingPlantFactory,
        assertionCreator
    )

    val methodCallRepresentation = coreFactory.newMethodCallFormatter().format("get", arrayOf<Any?>(key))

    return AssertImpl.builder.feature
        .withDescriptionAndRepresentation(Untranslatable(methodCallRepresentation())) {
            if (holds) plant.subject[key] ?: RawString.NULL
            else RawString.create(DescriptionMapAssertion.KEY_DOES_NOT_EXIST)
        }
        .withAssertions(
            AssertImpl.builder.createDescriptive(DescriptionMapAssertion.KEY_EXISTS, true) { holds },
            assertion
        )
        .build()
}
