package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec(
        AssertionVerbFactory,
        getNotOrAtMostTriple(),
        getContainsNotPair(),
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$contains $what $notOrAtMost $times" } to
                ("$contains.$notOrAtMost" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(
            plant: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) = plant.contains.inAnyOrder.notOrAtMost(atMost).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $notOrAtMost($times)"

    }
}
