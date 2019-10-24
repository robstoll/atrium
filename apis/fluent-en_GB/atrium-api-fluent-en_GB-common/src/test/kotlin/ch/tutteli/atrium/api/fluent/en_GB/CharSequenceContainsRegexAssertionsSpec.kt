package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
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

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast.$regex" to ::containsAtLeastString)

        private fun getAtLeastTripleRegex() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast.$regex" to ::containsAtLeastRegex)

        private fun containsAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = expect.contains.atLeast(atLeast).regex(a, *aX)

        private fun containsAtLeastRegex(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            expect.contains.atLeast(atLeast).regex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$atLeast.$ignoringCase.$regex" to ::containsAtLeastIgnoringCase)

        private fun getAtLeastIgnoringCaseTripleRegex() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$atLeast.$ignoringCase.$regex" to ::containsAtLeastIgnoringCase)

        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1) expect.contains.ignoringCase.regex(a, *aX)
        else expect.contains.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to ::containsShortcutString)

        private fun getShortcutTripleRegex() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                (containsRegex to ::containsShortcutRegex)

        private fun containsShortcutString(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a, *aX)

        private fun containsShortcutRegex(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost.$regex" to ::containsAtMostString)

        private fun getAtMostTripleRegex() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$atMost.$regex" to ::containsAtMostRegex)

        private fun containsAtMostString(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost.$regex" to ::containsAtMostIgnoringCase)

        private fun getAtMostIgnoringCaseTripleRegex() =
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" } to
                ("$contains.$ignoringCase.$atMost.$regex" to ::containsAtMostIgnoringCase)

        private fun containsAtMostRegex(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.contains.atMost(atMost).regex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun containsAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.contains.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
