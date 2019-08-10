package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect


class CharSequenceContainsAtMostAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtMostAssertionsSpec(
    AssertionVerbFactory,
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost" to Companion::containsAtMost)

        private fun containsAtMost(plant: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.atMost(atMost).values(a, *aX)

        private fun getAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost" to Companion::containsAtMostIgnoringCase)

        private fun containsAtMostIgnoringCase(plant: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.ignoringCase.atMost(atMost).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atMost($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atMost($times); $atMost($times) defines implicitly $atLeast($times) as well"
    }
}
