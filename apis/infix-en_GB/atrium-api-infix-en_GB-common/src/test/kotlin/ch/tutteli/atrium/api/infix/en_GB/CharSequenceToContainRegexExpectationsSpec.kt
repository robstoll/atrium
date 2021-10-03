package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CharSequenceToContainRegexExpectationsSpec : Spek({
    include(StringSpec)
    include(RegexSpec)


    describe("context 'aaaa'") {
        it("search for 'aa' finds 3 hits since we want non disjoint matches") {
            expect("aaaa") toContain o exactly 3 regex "aa"
        }
        it("search for 'aa?' finds 4 hits since we want non disjoint matches") {
            expect("aaaa") toContain o exactly 4 regex "aa?"
        }
    }
}) {
    object StringSpec : ch.tutteli.atrium.specs.integration.CharSequenceToContainRegexExpectationsSpec(
        getNameContainsRegex(),
        getAtLeastTripleString(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleString(),
        getAtMostTripleString(),
        getAtMostIgnoringCaseTripleString(),
        "[StringSpec] "
    )

    object RegexSpec : ch.tutteli.atrium.specs.integration.CharSequenceToContainRegexExpectationsSpec(
        getNameContainsRegex(),
        getAtLeastTripleRegex(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleRegex(),
        getAtMostTripleRegex(),
        getAtMostIgnoringCaseTripleString(),
        "[RegexSpec] "
    )

    companion object : CharSequenceToContainSpecBase() {

        private fun getNameContainsRegex() = "$toContain with search mode $regex"

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain o $atLeast $regex" to ::toContainAtLeastString)

        private fun getAtLeastTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain o $atLeast $regex" to ::toContainAtLeastRegex)

        private fun toContainAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContain o atLeast atLeast regex a
            else expect toContain o atLeast atLeast the regexPatterns(a, *aX)

        private fun toContainAtLeastRegex(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            if (aX.isEmpty()) expect toContain o atLeast atLeast matchFor Regex(a)
            else expect toContain o atLeast atLeast matchFor all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" } to
                ("$toContain o $atLeast $ignoringCase $regex" to ::toContainAtLeastIgnoringCase)

        private fun toContainAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case regex a
                else expect toContain o ignoring case atLeast atLeast regex a
            } else {
                if (atLeast == 1) expect toContain o ignoring case the regexPatterns(a, *aX)
                else expect toContain o ignoring case atLeast atLeast the regexPatterns(a, *aX)
            }

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::containsShortcutString)

        private fun getShortcutTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::containsShortcutRegex)

        private fun containsShortcutString(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContainRegex a
            else expect toContain regexPatterns(a, *aX)

        private fun containsShortcutRegex(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContain Regex(a)
            else expect toContain all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())


        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $regex" to ::toContainAtMostString)

        private fun getAtMostTripleRegex() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $regex" to ::toContainAtMostRegex)

        private fun toContainAtMostString(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContain o atMost atMost regex a
            else expect toContain o atMost atMost the regexPatterns(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain o $ignoringCase $atMost $regex" to ::toContainAtMostIgnoringCase)

        private fun toContainAtMostRegex(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContain o atMost atMost matchFor Regex(a)
            else expect toContain o atMost atMost matchFor all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())

        private fun toContainAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect toContain o ignoring case atMost atMost regex a
            else expect toContain o ignoring case atMost atMost the regexPatterns(a, *aX)
    }
}
