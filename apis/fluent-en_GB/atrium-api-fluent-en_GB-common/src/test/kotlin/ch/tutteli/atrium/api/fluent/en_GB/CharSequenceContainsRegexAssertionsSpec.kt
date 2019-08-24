package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsRegexAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsRegexAssertionsSpec(
        getNameContainsRegex(),
        getAtLeastTriple(),
        getAtLeastIgnoringCaseTriple(),
        getShortcutTriple(),
        getAtMostTriple(),
        getAtMostIgnoringCaseTriple(),
        "◆ ", "⚬ "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTriple() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast.$regex" to Companion::containsAtLeast)

        private fun containsAtLeast(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            expect.contains.atLeast(atLeast).regex(a, *aX)

        private fun getAtLeastIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$atLeast.$ignoringCase.$regex" to Companion::containsAtLeastIgnoringCase)

        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1) expect.contains.ignoringCase.regex(a, *aX)
        else expect.contains.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTriple() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to Companion::containsShortcut)

        private fun containsShortcut(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a, *aX)

        private fun getAtMostTriple() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost.$regex" to Companion::containsAtMost)

        private fun containsAtMost(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost.$regex" to Companion::containsAtMostIgnoringCase)

        private fun containsAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.contains.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
