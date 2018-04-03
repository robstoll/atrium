package ch.tutteli.atrium.domain.robstoll.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors._collectAssertions

class AssertionCollectorImpl : AssertionCollector {
    override fun <T : Any> collect(
        plant: AssertionPlant<T>,
        subPlantAndAssertionCreator: (CollectingAssertionPlant<T>) -> Unit
    ): AssertionGroup = _collectAssertions(plant, subPlantAndAssertionCreator)
}
