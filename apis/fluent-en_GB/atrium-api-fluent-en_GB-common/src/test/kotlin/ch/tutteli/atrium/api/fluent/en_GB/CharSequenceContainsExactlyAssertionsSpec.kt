package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsExactlyAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsExactlyAssertionsSpec(
        getExactlyTriple(),
        getExactlyIgnoringCaseTriple(),
        getContainsNotPair(),
        "◆ ", "⚬ "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$contains $what $exactly $times" } to
                ("$contains.$exactly" to Companion::containsExactly)

        private fun containsExactly(expect: Expect<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>) =
            expect.contains.exactly(exactly).values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $exactly $times" } to
                ("$contains.$ignoringCase.$exactly" to Companion::containsExactlyIgnoringCase)


        private fun containsExactlyIgnoringCase(
            expect: Expect<CharSequence>,
            exactly: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.exactly(exactly).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $exactly($times)"

    }
}
