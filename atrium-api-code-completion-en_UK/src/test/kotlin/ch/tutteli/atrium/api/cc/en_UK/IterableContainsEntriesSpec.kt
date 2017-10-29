package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsEntriesSpec(
    AssertionVerbFactory,
    getEntryPair(),
    getEntriesPair()
) {
    companion object {
        fun getEntryPair()
            = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderDecorator>::entry.name to Companion::entry

        fun getEntriesPair()
            = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderDecorator>::entries.name to Companion::entries

        private fun entry(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit)
            = plant.contains.inAnyOrder.atLeast(1).entry(a)

        private fun entries(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>)
            = plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
    }
}
