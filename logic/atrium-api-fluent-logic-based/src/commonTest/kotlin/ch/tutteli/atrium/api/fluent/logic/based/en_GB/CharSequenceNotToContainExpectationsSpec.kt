package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceNotToContainExpectationsSpec : ch.tutteli.atrium.specs.integration.CharSequenceNotToContainExpectationsSpec(
    getNotToContainTriple(),
    getNotToContainIgnoringCaseTriple()
) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getNotToContainTriple() =
            { what: String -> "$toContainNot $what" } to
                ("$toContainNot.$value/$values" to Companion::toContainNotFun)

        private fun toContainNotFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.notToContain.values(a, *aX)

        private fun getNotToContainIgnoringCaseTriple() =
            { what: String -> "$toContainNot $ignoringCase $what" } to
                ("$toContainNot.$ignoringCase.$value/$values" to Companion::toContainNotIgnoringCase)

        private fun toContainNotIgnoringCase(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            expect.notToContain.ignoringCase.values(a, *aX)
    }
}
