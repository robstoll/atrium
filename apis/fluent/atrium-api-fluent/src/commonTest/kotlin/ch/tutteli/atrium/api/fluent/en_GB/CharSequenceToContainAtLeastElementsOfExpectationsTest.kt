package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainAtLeastExpectationsTest
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class CharSequenceToContainAtLeastElementsOfExpectationsTest : AbstractCharSequenceToContainAtLeastExpectationsTest(
    getAtLeastElementsOfPair(),
    getAtLeastIgnoringCaseElementsOfTriple(),
    getAtLeastButAtMostElementsOfTriple(),
    getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
    getNotToContainPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    @Test
    fun trigger_run_gutter() = Unit

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory(
        subject = "hello",
        "$toContain.$atLeast(1).$elementsOf" to { input ->
            toContain.atLeast(1).elementsOf(input)
        },
        "$toContain.$ignoringCase.$atLeast(1).$elementsOf" to { input ->
            toContain.ignoringCase.atLeast(1).elementsOf(input)
        },
        "$toContain.$ignoringCase.$elementsOf" to { input ->
            toContain.ignoringCase.elementsOf(input)
        }
    )

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }

        internal fun getAtLeastElementsOfPair() =
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
