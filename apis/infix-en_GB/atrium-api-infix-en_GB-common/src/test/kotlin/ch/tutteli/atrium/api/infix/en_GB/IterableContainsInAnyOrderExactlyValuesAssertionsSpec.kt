package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderExactlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderExactlyValuesAssertionsSpec(
        getExactlyTriple(),
        getContainsNotPair(),
        "* "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$contains $what $exactly $times" } to
                ("$contains $filler $inAnyOrder $exactly" to Companion::containsExactly)

        private fun containsExactly(
            expect: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order exactly exactly value a
            else expect contains o inAny order exactly exactly the Values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNot` instead of `$exactly $times`"

    }
}
