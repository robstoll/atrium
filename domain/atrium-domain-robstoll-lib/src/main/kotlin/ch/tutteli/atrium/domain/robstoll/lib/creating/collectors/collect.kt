package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl

fun <T : Any> _collectAssertions(
    plant: AssertionPlant<T>,
    subPlantAndAssertionCreator: (CollectingAssertionPlant<T>) -> Unit
): AssertionGroup {
    val collectingPlant = AssertImpl.coreFactory.newCollectingPlant { plant.subject }
    subPlantAndAssertionCreator(collectingPlant)
    val collectedAssertions = collectingPlant.getAssertions()
    check(!collectedAssertions.isEmpty()) {
        "There was not any assertion created. Did you forget to add it to the plant?"
    }
    return AssertImpl.builder.invisibleGroup.create(collectedAssertions)
}
