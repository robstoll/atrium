package ch.tutteli.atrium.api.cc.en_UK

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
                plant.containsNot.value(a)
            } else {
                plant.containsNot.values(a, *aX)
            }
        }
    }
}
