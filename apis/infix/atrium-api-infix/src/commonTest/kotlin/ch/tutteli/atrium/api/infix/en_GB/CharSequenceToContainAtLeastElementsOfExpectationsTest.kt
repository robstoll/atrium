package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtLeastExpectationsTest
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class CharSequenceToContainAtLeastElementsOfExpectationsTest : AbstractCharSequenceToContainAtLeastExpectationsTest(
    getAtLeastElementsOfTriple(),
    getAtLeastIgnoringCaseElementsOfTriple(),
    getAtLeastButAtMostElementsOfTriple(),
    getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
    getNotToContainPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    @Test
    fun trigger_run_gutter() = 1

    @TestFactory
    fun toContainAtLeastOneElementsOf() =
        iterableLikeToIterableTestFactory("$toContain.$atLeast(1).$elementsOf", "hello", { input ->
        it toContain o atLeast 1 elementsOf input
    })

    @TestFactory
    fun toContainIgnoringCaseAtLeastOneElementsOf() =
        iterableLikeToIterableTestFactory("$toContain.$ignoringCase.$atLeast(1).$elementsOf", "hello", { input ->
        it toContain o ignoring case atLeast 1 elementsOf input
    })

    @TestFactory
    fun toContainIgnoringCaseElementsOf() =
        iterableLikeToIterableTestFactory("$toContain.$ignoringCase.$elementsOf", "hello", { input ->
        it toContain o ignoring case elementsOf input
    })

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$toContain o $atLeast $elementsOf" to Companion::toContainAtLeastElementsOf)

        private fun toContainAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect toContain o atLeast atLeast elementsOf listOf(a, *aX)

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$toContain o $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseElementsOfTriple() =
            atLeastIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $elementsOf" to Companion::toContainAtLeastIgnoringCaseElementsOf)

        private fun toContainAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case elementsOf listOf(a)
                else expect toContain o ignoring case atLeast atLeast elementsOf listOf(a)
            } else {
                if (atLeast == 1) expect toContain o ignoring case elementsOf listOf(a, *aX)
                else expect toContain o ignoring case atLeast atLeast elementsOf listOf(a, *aX)
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain o $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$toContain o $atLeast o $butAtMost $elementsOf" to Companion::toContainAtLeastButAtMostElementsOf)

        private fun toContainAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect toContain o atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $butAtMost $elementsOf" to Companion::toContainAtLeastButAtMostIgnoringCaseElementsOf)

        private fun toContainAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect toContain o ignoring case atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private fun getNotToContainPair() = notToContainValues to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContainValues` instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            string(timesButAtMost, timesAtLeast)

        private fun string(timesButAtMost: Int, timesAtLeast: Int): String =
            "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
