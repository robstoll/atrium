package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderNotOrAtMostObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderNotOrAtMostObjectsAssertionSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$toContain $notOrAtMost",
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: IAssertionPlant<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant to contain inAny order notOrAtMost atMost the Values(a, aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$notOrAtMost $times`"

    }
}
