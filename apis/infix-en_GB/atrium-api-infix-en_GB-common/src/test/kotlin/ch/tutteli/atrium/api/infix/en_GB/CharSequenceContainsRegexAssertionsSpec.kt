package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class CharSequenceContainsRegexAssertionsSpec : Spek({
    include(StringSpec)
    include(RegexSpec)
}) {
    object StringSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsRegexAssertionsSpec(
        getNameContainsRegex(),
        getAtLeastTripleString(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleString(),
        getAtMostTripleString(),
        getAtMostIgnoringCaseTripleString(),
        "* ", "- ",
        "[StringSpec] "
    )

    object RegexSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsRegexAssertionsSpec(
        getNameContainsRegex(),
        getAtLeastTripleRegex(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleRegex(),
        getAtMostTripleRegex(),
        getAtMostIgnoringCaseTripleString(),
        "* ", "- ",
        "[RegexSpec] "
    )

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains o $atLeast $regex" to ::containsAtLeastString)

        private fun getAtLeastTripleRegex() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains o $atLeast $regex" to ::containsAtLeastRegex)

        private fun containsAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect contains o atLeast atLeast regex a
            else expect contains o atLeast atLeast the regexPatterns(a, *aX)

        private fun containsAtLeastRegex(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            if (aX.isEmpty()) expect contains o atLeast atLeast matchFor Regex(a)
            else expect contains o atLeast atLeast matchFor all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains o $atLeast $ignoringCase $regex" to ::containsAtLeastIgnoringCase)

        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect contains o ignoring case regex a
                else expect contains o ignoring case atLeast atLeast regex a
            } else {
                if (atLeast == 1) expect contains o ignoring case the regexPatterns(a, *aX)
                else expect contains o ignoring case atLeast atLeast the regexPatterns(a, *aX)
            }

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to ::containsShortcutString)

        private fun getShortcutTripleRegex() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to ::containsShortcutRegex)

        private fun containsShortcutString(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect containsRegex a
            else expect containsRegex regexPatterns(a, *aX)

        private fun containsShortcutRegex(
            expect: Expect<CharSequence>,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect contains Regex(a)
            else expect contains all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())


        private fun getAtMostTripleString() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains o $atMost $regex" to ::containsAtMostString)

        private fun getAtMostTripleRegex() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains o $atMost $regex" to ::containsAtMostRegex)

        private fun containsAtMostString(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect contains o atMost atMost regex a
            else expect contains o atMost atMost the regexPatterns(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains o $ignoringCase $atMost $regex" to ::containsAtMostIgnoringCase)

        private fun containsAtMostRegex(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect contains o atMost atMost matchFor Regex(a)
            else expect contains o atMost atMost matchFor all(Regex(a), *aX.map { it.toRegex() }.toTypedArray())

        private fun containsAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) =
            if (aX.isEmpty()) expect contains o ignoring case atMost atMost regex a
            else expect contains o ignoring case atMost atMost the regexPatterns(a, *aX)
    }
}
