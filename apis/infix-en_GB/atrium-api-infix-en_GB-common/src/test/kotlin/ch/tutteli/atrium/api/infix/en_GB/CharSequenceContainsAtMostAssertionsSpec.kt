package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect


class CharSequenceContainsAtMostAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsAtMostAssertionsSpec(
        getAtMostTriple(),
        getAtMostIgnoringCaseTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        "* ", "- "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains o $atMost" to Companion::containsAtMost)

        private fun containsAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains o atMost atMost value a
            else expect contains o atMost atMost the values(a, *aX)

        private fun getAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains o $ignoringCase $atMost" to Companion::containsAtMostIgnoringCase)

        private fun containsAtMostIgnoringCase(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains o ignoring case atMost atMost value a
            else expect contains o ignoring case atMost atMost the values(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNotValues` instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
