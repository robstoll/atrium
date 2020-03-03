package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsNotOrAtMostAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsNotOrAtMostAssertionsSpec(
        getNotOrAtMostTriple(),
        getNotOrAtMostIgnoringCaseTriple(),
        getContainsNotPair(),
        "* ", "- "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$contains $what $notOrAtMost $times" } to
                ("$contains o $notOrAtMost" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains o notOrAtMost atMost value a
            else expect contains o notOrAtMost atMost the Values(a, *aX)

        private fun getNotOrAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $notOrAtMost $times" } to
                ("$contains o $ignoringCase $notOrAtMost" to Companion::containsNotOrAtMostIgnoringCase)

        private fun containsNotOrAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect contains o ignoring case notOrAtMost atMost value a
            else expect contains o ignoring case notOrAtMost atMost the Values(a, *aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNotValues` instead of `$notOrAtMost $times`"

    }
}
