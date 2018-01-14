package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

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

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaeltNicht.wert(a)
            } else {
                plant.enthaeltNicht.werte(a, *aX)
            }
        }
    }
}
