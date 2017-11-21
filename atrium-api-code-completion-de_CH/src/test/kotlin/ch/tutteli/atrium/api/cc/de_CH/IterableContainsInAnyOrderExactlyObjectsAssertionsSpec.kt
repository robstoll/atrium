package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderExactlyObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderExactlyObjectsAssertionSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$contains.$inAnyOrder.$exactly",
            { what: String, times: String -> "$contains $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: IAssertionPlant<Iterable<Double>>, exactly: Int, a: Double, aX: Array<out Double>)
            = plant.enthaelt.inAnyOrder.genau(exactly).objects(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $exactly($times)"

    }
}
