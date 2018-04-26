package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderExactlyValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderExactlyValuesAssertionSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$toContain $inAnyOrder $exactly",
            { what: String, times: String -> "$toContain $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<Iterable<Double>>, exactly: Int, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order exactly exactly `object` a
            } else {
                plant to contain inAny order exactly exactly the Objects(a, *aX)
            }
        }

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$exactly $times`"

    }
}
