package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceNotToContainExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractCharSequenceNotToContainExpectationsTest(
    getNotToContainTriple(),
    getNotToContainIgnoringCaseTriple()
) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getNotToContainTriple() =
            { what: String -> "$notToContainValues $what" } to
                (notToContainValues to Companion::notToContainFun)

        private fun notToContainFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)

        private fun getNotToContainIgnoringCaseTriple() =
            { what: String -> "$notToContainValues $ignoringCase $what" } to
                ("$notToContainValues o $ignoringCase" to Companion::notToContainIgnoringCase)

        private fun notToContainIgnoringCase(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain o ignoring case value a
            else expect notToContain o ignoring case the values(a, *aX)
    }
}
