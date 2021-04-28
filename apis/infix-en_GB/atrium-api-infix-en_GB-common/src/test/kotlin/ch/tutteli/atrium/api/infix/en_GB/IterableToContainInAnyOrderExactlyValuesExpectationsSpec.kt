package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableToContainInAnyOrderExactlyValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderExactlyValuesExpectationsSpec(
        getExactlyTriple(),
        getNotToContainPair()
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$toContain $what $exactly $times" } to
                ("$toContain $filler $inAnyOrder $exactly" to Companion::containsExactly)

        private fun containsExactly(
            expect: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order exactly exactly value a
            else expect contains o inAny order exactly exactly the values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$exactly $times`"

    }
}
