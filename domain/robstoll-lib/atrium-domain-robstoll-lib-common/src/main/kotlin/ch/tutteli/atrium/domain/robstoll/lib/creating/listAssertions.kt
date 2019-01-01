package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectOrExplain
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionListAssertion

fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): Assertion = getExisting(plant, index, coreFactory::newCollectingPlant, assertionCreator)


fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): Assertion = getExisting(plant, index, coreFactory::newCollectingPlantNullable, assertionCreator)

private fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> getExisting(
    plant: AssertionPlant<List<T>>,
    index: Int,
    collectingPlantFactory: (() -> T) -> C,
    assertionCreator: C.() -> Unit
): Assertion {
    //TODO, same as MAP, generalise to extract
    val holds = try {
        index < plant.subject.size
    } catch (e: PlantHasNoSubjectException) {
        true //TODO that's a hack, do we have a better solution?
    }

    val assertion: AssertionGroup = AssertImpl.collector.collectOrExplain(
        holds,
        DescriptionListAssertion.CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS,
        { (plant.subject[index]) },
        collectingPlantFactory,
        assertionCreator
    )

    val methodCallRepresentation = coreFactory.newMethodCallFormatter().format("get", arrayOf(index))

    return AssertImpl.builder.feature
        .withDescriptionAndRepresentation(Untranslatable(methodCallRepresentation())) {
            if (holds) plant.subject[index] ?: RawString.NULL
            else RawString.create(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
        }
        .withAssertions(
            AssertImpl.builder.createDescriptive(DescriptionListAssertion.INDEX_WITHIN_BOUND, true) { holds },
            assertion
        )
        .build()
}
