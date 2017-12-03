package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderOnlyEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>)
            = plant.enthaelt.inBeliebigerReihenfolge.nur.eintraege(a, *aX)
    }
}
