package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtLeastExpectationsTest

class CharSequenceToContainAtLeastExpectationsWithElementsTest : AbstractCharSequenceToContainAtLeastExpectationsTest(
    getAtLeastElementsOfTriple(),
    getAtLeastIgnoringCaseElementsOfTriple(),
    getAtLeastButAtMostElementsOfTriple(),
    getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
    getNotToContainPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$toContain.$atLeast.$elementsOf" to Companion::toContainAtLeastElementsOf)

        private fun toContainAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect.toContain.atLeast(atLeast).elementsOf(listOf(a, *aX))

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseElementsOfTriple() =
            atLeastIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$elementsOf" to Companion::toContainAtLeastIgnoringCaseElementsOf)

        private fun toContainAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect.toContain.ignoringCase.elementsOf(listOf(a))
                else expect.toContain.ignoringCase.atLeast(atLeast).elementsOf(listOf(a))
            } else {
                if (atLeast == 1) expect.toContain.ignoringCase.elementsOf(listOf(a, *aX))
                else expect.toContain.ignoringCase.atLeast(atLeast).elementsOf(listOf(a, *aX))
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$toContain.$atLeast.$butAtMost.$elementsOf" to Companion::toContainAtLeastButAtMostElementsOf)

        private fun toContainAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.toContain.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$butAtMost.$elementsOf" to Companion::toContainAtLeastButAtMostIgnoringCaseElementsOf)

        private fun toContainAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.toContain.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private fun getNotToContainPair() = toContainNot to ::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
