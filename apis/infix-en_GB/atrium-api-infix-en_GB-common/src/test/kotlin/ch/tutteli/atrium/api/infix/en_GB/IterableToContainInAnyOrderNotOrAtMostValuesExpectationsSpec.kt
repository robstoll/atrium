package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableToContainInAnyOrderNotOrAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderNotOrAtMostValuesExpectationsSpec(
        getNotOrAtMostTriple(),
        getNotToContainPair()
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" } to
                ("$toContain $filler $notOrAtMost" to Companion::notToContainOrAtMost)

        private fun notToContainOrAtMost(
            expect: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) =
            if (aX.isEmpty()) expect toContain o inAny order notOrAtMost atMost value a
            else expect toContain o inAny order notOrAtMost atMost the values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$notOrAtMost $times`"

    }
}
