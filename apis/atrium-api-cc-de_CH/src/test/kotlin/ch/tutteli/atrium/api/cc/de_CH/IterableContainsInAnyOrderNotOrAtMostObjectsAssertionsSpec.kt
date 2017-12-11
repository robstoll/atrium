package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderNotOrAtMostObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderNotOrAtMostObjectsAssertionSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$contains.$notOrAtMost",
            { what: String, times: String -> "$contains $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: IAssertionPlant<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant.enthaelt.inBeliebigerReihenfolge.nichtOderHoechstens(atMost).werte(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $notOrAtMost($times)"

    }
}
