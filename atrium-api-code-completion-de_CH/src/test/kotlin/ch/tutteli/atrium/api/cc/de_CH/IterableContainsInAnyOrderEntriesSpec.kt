package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderEntriesSpec(
    AssertionVerbFactory,
    getEntryPair(),
    getEntriesPair()
) {
    companion object {
        fun getEntryPair()
            = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderDecorator>::eintrag.name to Companion::entry

        fun getEntriesPair()
            = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderDecorator>::eintraege.name to Companion::entries

        private fun entry(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit)
            = plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(a)

        private fun entries(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>)
            = plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(a, *aX)
    }
}
