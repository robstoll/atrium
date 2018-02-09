package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderAtLeastObjectsAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeastObjectsAssertionSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    getAtLeastButAtMostTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    companion object : IterableContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$toContain $inAnyOrder $atLeast",
            { what: String, times: String -> "$toContain $what in any order $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<Iterable<Double>>, atLeast: Int, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast atLeast value a
            } else {
                plant to contain inAny order atLeast atLeast the Values(a, *aX)
            }
        }

        private fun getAtLeastButAtMostTriple() = Triple(
            "$toContain $atLeast $butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMost
        )

        private fun containsAtLeastButAtMost(plant: Assert<Iterable<Double>>, atLeast: Int, butAtMost: Int, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast atLeast butAtMost butAtMost `object` a
            } else {
                plant to contain inAny order atLeast atLeast butAtMost butAtMost the Values(a, *aX)
            }
        }

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        private fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
            = "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
