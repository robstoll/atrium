package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderAtMostObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderAtMostObjectsAssertionSpec(
    AssertionVerbFactory,
    getAtMostTriple(),
    getContainsNotPair(),
    getExactlyPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getAtMostTriple() = Triple(
            "$contains.$inAnyOrder.$atMost",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant.enthaelt.inBeliebigerReihenfolge.hoechstens(atMost).werte(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atMost($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atMost($times); $atMost($times) defines implicitly $atLeast($times) as well"
    }
}
