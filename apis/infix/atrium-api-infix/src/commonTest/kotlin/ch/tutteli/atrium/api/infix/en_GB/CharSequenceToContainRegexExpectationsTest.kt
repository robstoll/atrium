package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.*
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
        expectText toContain o atLeast 1 regex "[hH][ea]llo"
        expectText toContain o atMost 20 regex "[a-z]"
    }

    companion object : CharSequenceToContainSpecBase() {

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain o $atLeast $regex" to ::toContainAtLeastString)

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain ignoring case $what $atLeast $times" } to
                ("$toContain o $atLeast $ignoringCase $regex" to ::toContainAtLeastIgnoringCase)

        private fun getShortcutTripleString() =
            { what: String, _: String -> "$toContain $what" } to
                (toContainRegex to ::containsShortcutString)

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $regex" to ::toContainAtMostString)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain ignoring case $what $atMost $times" } to
                ("$toContain o $ignoringCase $atMost $regex" to ::toContainAtMostIgnoringCase)

        private fun toContainAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atLeast atLeast regex a
            else expect toContain o atLeast atLeast the regexPatterns(a, *aX)

        private fun toContainAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case regex a
                else expect toContain o ignoring case atLeast atLeast regex a
            } else {
                if (atLeast == 1) expect toContain o ignoring case the regexPatterns(a, *aX)
                else expect toContain o ignoring case atLeast atLeast the regexPatterns(a, *aX)
            }

        private fun containsShortcutString(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContainRegex a
            else expect toContain regexPatterns(a, *aX)

        private fun toContainAtMostString(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atMost atMost regex a
            else expect toContain o atMost atMost the regexPatterns(a, *aX)

        private fun toContainAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o ignoring case atMost atMost regex a
            else expect toContain o ignoring case atMost atMost the regexPatterns(a, *aX)
    }
}
