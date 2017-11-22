package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInOrderOnlyObjectsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaelt.inGegebenerReihenfolge.nur.werte(a, *aX)
    }
}
