package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtLeastExpectationsTest
import kotlin.test.Test

class CharSequenceToContainAtLeastValuesExpectationsTest : AbstractCharSequenceToContainAtLeastExpectationsTest(
    getAtLeastValuesTriple(),
    getAtLeastIgnoringCaseValuesTriple(),
    getAtLeastButAtMostValuesTriple(),
    getAtLeastBustAtMostIgnoringCaseValuesTriple(),
    getNotToContainPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {
    @Test
    fun trigger_run_gutter() = Unit

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }
        internal fun getAtLeastValuesTriple() =
            atLeastDescr to ("$toContain.$atLeast.$value/$values" to Companion::toContainAtLeast)

        private fun toContainAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect.toContain.atLeast(atLeast).value(a)
            else expect.toContain.atLeast(atLeast).values(a, *aX)

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseValuesTriple() =
            atLeastIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$value/$values" to Companion::toContainAtLeastIgnoringCaseValues)

        private fun toContainAtLeastIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect.toContain.ignoringCase.value(a)
                else expect.toContain.ignoringCase.atLeast(atLeast).value(a)
            } else {
                if (atLeast == 1) expect.toContain.ignoringCase.values(a, *aX)
                else expect.toContain.ignoringCase.atLeast(atLeast).values(a, *aX)
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostValuesTriple() =
            atLeastButAtMostDescr to ("$toContain.$atLeast.$butAtMost.$value/$values" to Companion::toContainAtLeastButAtMostValues)

        private fun toContainAtLeastButAtMostValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.toContain.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastBustAtMostIgnoringCaseValuesTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$butAtMost.$value/$values" to Companion::toContainAtLeastButAtMostIgnoringCaseValues)

        private fun toContainAtLeastButAtMostIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.toContain.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getNotToContainPair() = toContainNot to ::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
