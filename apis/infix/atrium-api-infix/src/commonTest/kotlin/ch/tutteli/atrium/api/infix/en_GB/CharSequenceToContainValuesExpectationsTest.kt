package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.Fun3
import ch.tutteli.atrium.specs.integration.*
import kotlin.test.Test

class CharSequenceToContainValuesExpectationsTest : AbstractCharSequenceToContainValuesExpectationsTest(
    getAtLeastTripleString(),
    getAtLeastIgnoringCaseTripleString(),
    getShortcutTripleString(),
    getAtMostTripleString(),
    getAtMostIgnoringCaseTripleString()
) {
    @Test
    fun ambiguityTest() {
        val expectText = expect("Hello my name is Robert")
        expectText toContain o atLeast 1 value "Hello"
        expectText toContain o atMost 10 value "name"
    }

    companion object : CharSequenceToContainSpecBase() {
        private fun getAtLeastTripleString(): Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>> =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain o $atLeast $value" to fun Expect<CharSequence>.(atLeast: Int, a: String, aX: Array<out String>): Expect<CharSequence> {
                    this toContain o atLeast atLeast value a
                    return this
                })

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain ignoring case $what $atLeast $times" } to
                ("$toContain o $atLeast $ignoringCase $value" to ::toContainAtLeastIgnoringCase)

        private fun getShortcutTripleString() =
            { what: String, _: String -> "$toContain $what" } to
                (toContain to ::containsShortcutString)

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $value" to ::toContainAtMostString)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain ignoring case $what $atMost $times" } to
                ("$toContain o $ignoringCase $atMost $value" to ::toContainAtMostIgnoringCase)

        private fun toContainAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atLeast atLeast value a
            else expect toContain o atLeast atLeast the values(a, *aX)

        private fun toContainAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case value a
                else expect toContain o ignoring case atLeast atLeast value a
            } else {
                if (atLeast == 1) expect toContain o ignoring case the values(a, *aX)
                else expect toContain o ignoring case atLeast atLeast the values(a, *aX)
            }

        private fun containsShortcutString(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain values(a)
            else expect toContain values(a, *aX)

        private fun toContainAtMostString(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atMost atMost value a
            else expect toContain o atMost atMost the values(a, *aX)

        private fun toContainAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o ignoring case atMost atMost value a
            else expect toContain o ignoring case atMost atMost the values(a, *aX)

    }
}
