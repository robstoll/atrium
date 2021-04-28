package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableToContainInAnyOrderAtLeastValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeastValuesExpectationsSpec(
        getAtLeastTriple(),
        getAtLeastButAtMostTriple(),
        getNotToContainPair(),
        getExactlyPair(),
        Companion::getErrorMsgAtLeastButAtMost
    ) {

    companion object : IterableContainsSpecBase() {

        internal fun getAtLeastTriple() =
            { what: String, times: String -> "$toContain $what in any order $atLeast $times" } to
                ("$toContain $filler $inAnyOrder $atLeast" to Companion::toContainAtLeast)

        private fun toContainAtLeast(
            expect: Expect<Iterable<Double>>,
            atLeast: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast atLeast value a
            else expect toContain o inAny order atLeast atLeast the values(a, *aX)


        private fun getAtLeastButAtMostTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$toContain $filler $atLeast $butAtMost" to Companion::toContainAtLeastButAtMost)

        private fun toContainAtLeastButAtMost(
            expect: Expect<Iterable<Double>>,
            atLeast: Int,
            butAtMost: Int,
            a: Double,
            aX: Array<out Double>
        ) = expect toContain o inAny order atLeast atLeast butAtMost butAtMost the values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
