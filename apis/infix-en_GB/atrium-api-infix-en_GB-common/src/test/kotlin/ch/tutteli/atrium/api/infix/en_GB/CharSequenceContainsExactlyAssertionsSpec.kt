package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsExactlyAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsExactlyAssertionsSpec(
        getExactlyTriple(),
        getExactlyIgnoringCaseTriple(),
        getContainsNotPair(),
        "* ", "- "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$contains $what $exactly $times" } to
                ("$contains o $exactly" to Companion::containsExactly)

        private fun containsExactly(expect: Expect<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains o exactly exactly value a
            else expect contains o exactly exactly the Values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $exactly $times" } to
                ("$contains o $ignoringCase $exactly" to Companion::containsExactlyIgnoringCase)


        private fun containsExactlyIgnoringCase(
            expect: Expect<CharSequence>,
            exactly: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect contains o ignoring case exactly exactly value a
            else expect contains o ignoring case exactly exactly the Values(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot
        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNotValues` instead of `$exactly $times`"
    }
}
