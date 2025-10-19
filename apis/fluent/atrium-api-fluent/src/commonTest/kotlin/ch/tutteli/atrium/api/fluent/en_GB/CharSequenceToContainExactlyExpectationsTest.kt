package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainExactlyExpectationsTest
import kotlin.test.Test

class CharSequenceToContainExactlyExpectationsTest : AbstractCharSequenceToContainExactlyExpectationsTest(
    getExactlyTriple(),
    getExactlyIgnoringCaseTriple(),
    getNotToContainPair()
) {
    @Test
    fun trigger_run_gutter() = Unit

    companion object : CharSequenceToContainSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$toContain $what $exactly $times" } to
                ("$toContain.$exactly.$value/$values" to Companion::toContainExactly)

        private fun toContainExactly(expect: Expect<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>) =
            expect.toContain.exactly(exactly).values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() =
            { what: String, times: String -> "$toContain $ignoringCase $what $exactly $times" } to
                ("$toContain.$ignoringCase.$exactly.$value/$values" to Companion::toContainExactlyIgnoringCase)

        private fun toContainExactlyIgnoringCase(
            expect: Expect<CharSequence>,
            exactly: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.toContain.ignoringCase.exactly(exactly).values(a, *aX)


        private fun getNotToContainPair() = toContainNot to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $exactly($times)"

    }
}
