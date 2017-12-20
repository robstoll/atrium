package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class IterableContainsInAnyOrderExactlyObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderExactlyObjectsAssertionSpec(
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

        private fun containsExactly(plant: AssertionPlant<Iterable<Double>>, exactly: Int, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
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
