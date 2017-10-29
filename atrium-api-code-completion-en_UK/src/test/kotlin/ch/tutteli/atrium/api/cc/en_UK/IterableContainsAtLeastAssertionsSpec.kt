package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsAtLeastAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsAtLeastAssertionSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    //TODO as soon as butAtMost is implemented
    //getAtLeastButAtMostTriple(),
    getContainsNotPair()
    //TODO as soon as exactly is implemented
    //getExactlyPair(),
    //Companion::getErrorMsgAtLeastButAtMost
) {

    companion object : IterableContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$contains.$inAnyOrder.$atLeast",
            { what: String, times: String -> "$contains $what in any order $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: IAssertionPlant<Iterable<Double>>, atLeast: Int, a: Double, aX: Array<out Double>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(atLeast).value(a)
            } else {
                plant.contains.inAnyOrder.atLeast(atLeast).values(a, *aX)
            }
        }

        //TODO as soon as butAtMost is implemented
//        private fun getAtLeastButAtMostTriple() = Triple(
//            "$contains.$atLeast.$butAtMost",
//            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
//            Companion::containsAtLeastButAtMost
//        )
//
//        private fun containsAtLeastButAtMost(plant: IAssertionPlant<Iterable<Double>>, atLeast: Int, butAtMost: Int, a: Double, aX: Array<out Double>)
//            = plant.contains.atLeast(atLeast).butAtMost(butAtMost).objects(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atLeast($times)"

        //TODO as soon as exactly is implemented
//        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

//        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"
//
//        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
//            = "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
