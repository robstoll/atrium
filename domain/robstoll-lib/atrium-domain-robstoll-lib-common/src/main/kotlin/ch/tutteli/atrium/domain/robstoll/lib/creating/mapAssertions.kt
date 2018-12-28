package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
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

fun <K, V : Any, T : Map<K, V>> _getExisting(
    plant: AssertionPlant<T>,
    key: K,
    assertionCreator: AssertionPlant<V>.() -> Unit
): Assertion {
    val holds = try {
        plant.subject.containsKey(key)
    } catch (e: PlantHasNoSubjectException) {
        true //TODO that's a hack, do we have a better solution?
    }

    //TODO that looks like a common pattern to me
    val assertion: AssertionGroup = if (holds) {
        AssertImpl.collector.collect({ plant.subject[key]!! }, assertionCreator)
    } else {
        val explanatoryAssertions = AssertImpl
            .collector
            .forExplanation
            .doNotThrowIfNoAssertionIsCollected
            .collect(DescriptionMapAssertion.CANNOT_EVALUATE_KEY_DOES_NOT_EXIST, assertionCreator, null)
        AssertImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertions)
            .build()
    }

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
