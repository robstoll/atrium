package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderAtMostValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtMostValuesAssertionSpec(
        getAtMostTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        "* "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains $filler $inAnyOrder $atMost" to Companion::containsAtMost)

        private fun containsAtMost(expect: Expect<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>) =
            if(aX.isEmpty()) expect contains o inAny order atMost atMost value a
            else expect contains o inAny order atMost atMost the Values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNot` instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
