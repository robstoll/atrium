package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsNotAssertionsSpec(
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple(),
    "* ", "- "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() =
            { what: String -> "$containsNotValues $what" } to
                (containsNotValues to Companion::containsNotFun)

        private fun containsNotFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect containsNot o value a
            else expect containsNot o the Values(
                a,
                *aX
            )

        private fun getContainsNotIgnoringCaseTriple() =
            { what: String -> "$containsNotValues $ignoringCase $what" } to
                ("$containsNotValues o $ignoringCase" to Companion::containsNotIgnoringCase)

        private fun containsNotIgnoringCase(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect containsNot o ignoring case value a
            else expect containsNot o ignoring case the Values(
                a,
                *aX
            )
    }
}
