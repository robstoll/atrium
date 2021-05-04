package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableToContainInAnyOrderAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtMostValuesExpectationsSpec(
        getAtMostTriple(),
        getNotToContainPair(),
        getExactlyPair()
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain $filler $inAnyOrder $atMost" to Companion::toContainAtMost)

        private fun toContainAtMost(expect: Expect<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>) =
            if(aX.isEmpty()) expect toContain o inAny order atMost atMost value a
            else expect toContain o inAny order atMost atMost the values(a, *aX)


        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
