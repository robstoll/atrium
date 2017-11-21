package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderAtLeastObjectsAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderAtLeastObjectsAssertionSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    getAtLeastButAtMostTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    companion object : IterableContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$contains.$inAnyOrder.$atLeast",
            { what: String, times: String -> "$contains $what in any order $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: IAssertionPlant<Iterable<Double>>, atLeast: Int, a: Double, aX: Array<out Double>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inAnyOrder.zumindest(atLeast).value(a)
            } else {
                plant.enthaelt.inAnyOrder.zumindest(atLeast).values(a, *aX)
            }
        }

        private fun getAtLeastButAtMostTriple() = Triple(
            "$contains.$atLeast.$butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMost
        )

        private fun containsAtLeastButAtMost(plant: IAssertionPlant<Iterable<Double>>, atLeast: Int, butAtMost: Int, a: Double, aX: Array<out Double>)
            = plant.enthaelt.inAnyOrder.zumindest(atLeast).aberHoechstens(butAtMost).objects(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
            = "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
