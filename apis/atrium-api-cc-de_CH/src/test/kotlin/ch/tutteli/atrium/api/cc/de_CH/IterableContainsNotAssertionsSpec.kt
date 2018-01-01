package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class IterableContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsNotTriple()
) {

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotTriple() = Triple(
            containsNot,
            { what: String -> "$containsNot $what" },
            Companion::containsNotFun
        )

        private fun containsNotFun(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaeltNicht.wert(a)
            } else {
                plant.enthaeltNicht.werte(a, *aX)
            }
        }
    }
}
