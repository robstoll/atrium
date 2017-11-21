package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInOrderOnlyEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>)
            = plant.enthaelt.inOrder.only.entries(a, *aX)
    }
}
