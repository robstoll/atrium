package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderOnlyObjectsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyObjectsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.werte(a, *aX)
            }
        }
    }
}
