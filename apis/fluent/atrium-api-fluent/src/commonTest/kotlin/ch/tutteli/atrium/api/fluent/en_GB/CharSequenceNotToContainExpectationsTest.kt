package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceNotToContainExpectationsTest
import kotlin.test.Test

class CharSequenceNotToContainExpectationsTest : AbstractCharSequenceNotToContainExpectationsTest(
    getNotToContainTriple(),
    getNotToContainIgnoringCaseTriple()
) {
    @Test
    fun trigger_run_gutter() = 1

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
