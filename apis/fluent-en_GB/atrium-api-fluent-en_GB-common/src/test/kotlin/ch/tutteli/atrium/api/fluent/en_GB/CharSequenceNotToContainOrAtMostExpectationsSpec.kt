package ch.tutteli.atrium.api.fluent.en_GB

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
                ("$toContain.$notOrAtMost.$value/$values" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            expect.contains.notOrAtMost(atMost).values(a, *aX)

        private fun getNotOrAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $notOrAtMost $times" } to
                ("$toContain.$ignoringCase.$notOrAtMost.$value/$values" to Companion::containsNotOrAtMostIgnoringCase)

        private fun containsNotOrAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.notOrAtMost(atMost).values(a, *aX)


        private fun getNotToContainPair() = toContainNot to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $notOrAtMost($times)"

    }
}
