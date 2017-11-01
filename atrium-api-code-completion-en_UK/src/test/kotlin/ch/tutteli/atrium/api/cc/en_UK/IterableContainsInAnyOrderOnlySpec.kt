package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderOnlySpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlySpec(
    AssertionVerbFactory,
    getContainsPair(),
    "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.contains.inAnyOrder.only.values(a, *aX)
    }
}
