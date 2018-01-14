package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderOnlyObjectsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyObjectsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.only.value(a)
            } else {
                plant.contains.inAnyOrder.only.values(a, *aX)
            }
        }
    }
}
