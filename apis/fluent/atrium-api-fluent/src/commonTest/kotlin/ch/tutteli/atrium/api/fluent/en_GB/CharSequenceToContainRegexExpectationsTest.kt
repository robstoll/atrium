package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainRegexExpectationsTest
import kotlin.test.Test

class CharSequenceToContainRegexExpectationsTest : AbstractCharSequenceToContainRegexExpectationsTest(
    getAtLeastTripleString(),
    getAtLeastIgnoringCaseTripleString(),
    getShortcutTripleString(),
    getAtMostTripleString(),
    getAtMostIgnoringCaseTripleString()
) {
    @Test
    fun ambiguityTest() {
        val expectText = expect("Hello my name is Robert")
        expectText.toContainRegex("[hH][ea]llo")
        expectText.toContain.atLeast(1).regex("Roberto?")
        expectText.toContain.ignoringCase.atMost(20).regex("[a-z]")
    }

    @Test
    fun context_aaaa_regex_aa_finds_3_non_disjoint_hits() {
        expect("aaaa").toContain.exactly(3).regex("aa")
    }

    @Test
    fun context_aaaa_regex_aaQ_finds_4_non_disjoint_hits() {
        expect("aaaa").toContain.exactly(4).regex("aa?")
    }

    companion object : CharSequenceToContainSpecBase() {

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain.$atLeast.$regex" to ::toContainAtLeastString)

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" } to
                ("$toContain.$atLeast.$ignoringCase.$regex" to ::toContainAtLeastIgnoringCase)

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::toContainShortcutString)

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$regex" to ::toContainAtMostString)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain.$ignoringCase.$atMost.$regex" to ::toContainAtMostIgnoringCase)

        private fun toContainAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = expect.toContain.atLeast(atLeast).regex(a, *aX)

        private fun toContainAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1)
            expect.toContain.ignoringCase.regex(a, *aX)
        else
            expect.toContain.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun toContainShortcutString(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ) = expect.toContainRegex(a, *aX)

        private fun toContainAtMostString(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.toContain.atMost(atMost).regex(a, *aX)

        private fun toContainAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.toContain.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
