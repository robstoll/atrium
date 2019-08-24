package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsNotAssertionsSpec(
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() =
            { what: String -> "$containsNot $what" } to
                (containsNot to Companion::containsNotFun)

        private fun containsNotFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.containsNot.values(a, *aX)

        private fun getContainsNotIgnoringCaseTriple() =
            { what: String -> "$containsNot $ignoringCase $what" } to
                ("$containsNot.$ignoringCase" to Companion::containsNotIgnoringCase)

        private fun containsNotIgnoringCase(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.containsNot.ignoringCase.values(a, *aX)
    }
}
