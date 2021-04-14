package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceNotToContainExpectationsSpec : ch.tutteli.atrium.specs.integration.CharSequenceNotToContainExpectationsSpec(
    getNotToContainTriple(),
    getNotToContainIgnoringCaseTriple()
) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getNotToContainTriple() =
            { what: String -> "$toContainNot $what" } to
                ("$toContainNot.$value/$values" to Companion::containsNotFun)

        private fun containsNotFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.containsNot.values(a, *aX)

        private fun getNotToContainIgnoringCaseTriple() =
            { what: String -> "$toContainNot $ignoringCase $what" } to
                ("$toContainNot.$ignoringCase.$value/$values" to Companion::containsNotIgnoringCase)

        private fun containsNotIgnoringCase(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.containsNot.ignoringCase.values(a, *aX)
    }
}
