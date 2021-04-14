package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceToContainExactlyExpectationsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceToContainExactlyExpectationsSpec(
        getExactlyTriple(),
        getExactlyIgnoringCaseTriple(),
        getNotToContainPair()
    ) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$toContain $what $exactly $times" } to
                ("$toContain.$exactly.$value/$values" to Companion::containsExactly)

        private fun containsExactly(expect: Expect<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>) =
            expect.contains.exactly(exactly).values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $exactly $times" } to
                ("$toContain.$ignoringCase.$exactly.$value/$values" to Companion::containsExactlyIgnoringCase)


        private fun containsExactlyIgnoringCase(
            expect: Expect<CharSequence>,
            exactly: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.exactly(exactly).values(a, *aX)


        private fun getNotToContainPair() = toContainNot to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $exactly($times)"

    }
}
