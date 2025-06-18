package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtMostExpectationsTest
import kotlin.test.Test

class CharSequenceToContainAtMostExpectationsTest : AbstractCharSequenceToContainAtMostExpectationsTest(
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    getNotToContainPair(),
    getExactlyPair()
) {
    @Test
    fun trigger_run_gutter() = 1

    companion object : CharSequenceToContainSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$value/$values" to Companion::toContainAtMost)

        private fun toContainAtMost(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            expect.toContain.atMost(atMost).values(a, *aX)

        private fun getAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain.$ignoringCase.$atMost.$value/$values" to Companion::toContainAtMostIgnoringCase)

        private fun toContainAtMostIgnoringCase(expect: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            expect.toContain.ignoringCase.atMost(atMost).values(a, *aX)


        private fun getNotToContainPair() = toContainNot to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $atMost($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atMost($times); $atMost($times) defines implicitly $atLeast($times) as well"
    }
}
