package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec(
        getNotOrAtMostTriple(),
        getContainsNotPair(),
        "* "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$contains $what $notOrAtMost $times" } to
                ("$contains $filler $notOrAtMost" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(
            expect: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) =
            if (aX.isEmpty()) expect contains o inAny order notOrAtMost atMost value a
            else expect contains o inAny order notOrAtMost atMost the values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNot` instead of `$notOrAtMost $times`"

    }
}
