package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtMostExpectationsTest


class CharSequenceToContainAtMostExpectationsTest :
    AbstractCharSequenceToContainAtMostExpectationsTest(
        getAtMostTriple(),
        getAtMostIgnoringCaseTriple(),
        getNotToContainPair(),
        getExactlyPair()
    ) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $value/$values" to Companion::toContainAtMost)

        private fun toContainAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o atMost atMost value a
            else expect toContain o atMost atMost the values(a, *aX)

        private fun getAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain o $ignoringCase $atMost $value/$values" to Companion::toContainAtMostIgnoringCase)

        private fun toContainAtMostIgnoringCase(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o ignoring case atMost atMost value a
            else expect toContain o ignoring case atMost atMost the values(a, *aX)

        private fun getNotToContainPair() = notToContainValues to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContainValues` instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
