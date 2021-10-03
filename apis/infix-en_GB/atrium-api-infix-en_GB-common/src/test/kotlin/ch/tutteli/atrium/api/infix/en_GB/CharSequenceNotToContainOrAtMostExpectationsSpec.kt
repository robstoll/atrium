package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceNotToContainOrAtMostExpectationsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceNotToContainOrAtMostExpectationsSpec(
        getNotOrAtMostTriple(),
        getNotOrAtMostIgnoringCaseTriple(),
        getNotToContainPair()
    ) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" } to
                ("$toContain o $notOrAtMost $value/$values" to Companion::notToContainOrAtMost)

        private fun notToContainOrAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o notOrAtMost atMost value a
            else expect toContain o notOrAtMost atMost the values(a, *aX)

        private fun getNotOrAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $notOrAtMost $times" } to
                ("$toContain o $ignoringCase $notOrAtMost $value/$values" to Companion::notToContainOrAtMostIgnoringCase)

        private fun notToContainOrAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect toContain o ignoring case notOrAtMost atMost value a
            else expect toContain o ignoring case notOrAtMost atMost the values(a, *aX)


        private fun getNotToContainPair() = notToContainValues to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContainValues` instead of `$notOrAtMost $times`"

    }
}
