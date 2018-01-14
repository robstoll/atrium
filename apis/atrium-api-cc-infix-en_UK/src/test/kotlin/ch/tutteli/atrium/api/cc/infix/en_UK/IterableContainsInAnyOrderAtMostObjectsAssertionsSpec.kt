package ch.tutteli.atrium.api.cc.infix.en_UK

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
            "$toContain $inAnyOrder $atMost",
            { what: String, times: String -> "$toContain $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant to contain inAny order atMost atMost the Values(a, *aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int)
            = "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
