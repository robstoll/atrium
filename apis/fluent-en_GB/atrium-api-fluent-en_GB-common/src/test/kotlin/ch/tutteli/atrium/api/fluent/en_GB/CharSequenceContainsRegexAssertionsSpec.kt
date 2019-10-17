package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek

private typealias StringFun =
    Pair<String, (Expect<CharSequence>, Int, String, Array<out String>) -> Expect<CharSequence>>

private typealias RegexFun = Pair<String, (Expect<CharSequence>, Int, Regex, Array<out Regex>) -> Expect<CharSequence>>

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
        "◆ ", "⚬ "
    )

    object RegexSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsRegexAssertionsSpec(
        getNameContainsRegex(),
        getAtLeastTripleRegex(),
        getAtLeastIgnoringCaseTripleRegex(),
        getShortcutTripleRegex(),
        getAtMostTripleRegex(),
        getAtMostIgnoringCaseTripleRegex(),
        "◆ ", "⚬ "
    )

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTripleString(): Pair<(String, String) -> String, StringFun> =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast.$regex" to Companion::containsAtLeast)

        private fun getAtLeastTripleRegex(): Pair<(Regex, String) -> String, RegexFun> =
            { what: Regex, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast.$regex" to Companion::containsAtLeast)

        private fun containsAtLeast(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            expect.contains.atLeast(atLeast).regex(a, *aX)

        private fun containsAtLeast(expect: Expect<CharSequence>, atLeast: Int, a: Regex, aX: Array<out Regex>) =
            expect.contains.atLeast(atLeast).regex(a, *aX)

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$atLeast.$ignoringCase.$regex" to Companion::containsAtLeastIgnoringCase)

        private fun getAtLeastIgnoringCaseTripleRegex() =
            { what: Regex, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$atLeast.$ignoringCase.$regex" to Companion::containsAtLeastIgnoringCase)

        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1) expect.contains.ignoringCase.regex(a, *aX)
        else expect.contains.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to Companion::containsShortcut)

        private fun getShortcutTripleRegex() =
            { what: Regex, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to Companion::containsShortcut)

        private fun containsShortcut(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a, *aX)

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost.$regex" to Companion::containsAtMost)

        private fun getAtMostTripleRegex() =
            { what: Regex, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost.$regex" to Companion::containsAtMost)

        private fun containsAtMost(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost.$regex" to Companion::containsAtMostIgnoringCase)

        private fun getAtMostIgnoringCaseTripleRegex() =
            { what: Regex, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost.$regex" to Companion::containsAtMostIgnoringCase)

        private fun containsAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.contains.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
