package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceToContainExactlyExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainExactlyExpectationsTest(
        getExactlyTriple(),
        getExactlyIgnoringCaseTriple(),
        getNotToContainPair()
    ) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$toContain $what $exactly $times" } to
                ("$toContain o $exactly $value/$values" to Companion::toContainExactly)

        private fun toContainExactly(expect: Expect<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o exactly exactly value a
            else expect toContain o exactly exactly the values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $exactly $times" } to
                ("$toContain o $ignoringCase $exactly $value/$values" to Companion::toContainExactlyIgnoringCase)


        private fun toContainExactlyIgnoringCase(
            expect: Expect<CharSequence>,
            exactly: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect toContain o ignoring case exactly exactly value a
            else expect toContain o ignoring case exactly exactly the values(a, *aX)

        private fun getNotToContainPair() = notToContainValues to Companion::getErrorMsgNotToContain
        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContainValues` instead of `$exactly $times`"
    }
}
