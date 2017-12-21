package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class IterableContainsInAnyOrderOnlyObjectsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyObjectsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.werte(a, *aX)
            }
        }
    }
}
