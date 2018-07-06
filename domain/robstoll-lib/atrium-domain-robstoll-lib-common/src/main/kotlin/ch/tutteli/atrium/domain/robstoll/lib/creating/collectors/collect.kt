package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

fun <T : Any> _collectAssertions(
    subjectProvider: () -> T,
    subPlantAndAssertionCreator: (CollectingAssertionPlant<T>) -> Unit
): AssertionGroup {
    val collectingPlant = AssertImpl.coreFactory.newCollectingPlant(subjectProvider)
    subPlantAndAssertionCreator(collectingPlant)
    val collectedAssertions = collectingPlant.getAssertions()
    check(!collectedAssertions.isEmpty()) {
        "There was not any assertion created. Did you forget to add it to the plant?"
    }
    return AssertImpl.builder.invisibleGroup.withAssertions(collectedAssertions).build()
}
