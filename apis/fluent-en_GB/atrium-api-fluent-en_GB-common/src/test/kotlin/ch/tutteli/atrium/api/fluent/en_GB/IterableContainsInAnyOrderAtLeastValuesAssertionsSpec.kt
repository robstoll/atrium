package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderAtLeastValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeastValuesAssertionSpec(
        getAtLeastTriple(),
        getAtLeastButAtMostTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        Companion::getErrorMsgAtLeastButAtMost,
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        internal fun getAtLeastTriple() =
            { what: String, times: String -> "$contains $what in any order $atLeast $times" } to
                ("$contains.$inAnyOrder.$atLeast" to Companion::containsAtLeast)

        private fun containsAtLeast(
            plant: Expect<Iterable<Double>>,
            atLeast: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.contains.inAnyOrder.atLeast(atLeast).value(a)
            else plant.contains.inAnyOrder.atLeast(atLeast).values(a, *aX)


        private fun getAtLeastButAtMostTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$contains.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMost)

        private fun containsAtLeastButAtMost(
            plant: Expect<Iterable<Double>>,
            atLeast: Int,
            butAtMost: Int,
            a: Double,
            aX: Array<out Double>
        ) = plant.contains.inAnyOrder.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
