package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CharSequenceContainsRegexExpectationsSpec : Spek({
    include(StringSpec)
    include(RegexSpec)

    describe("context 'aaaa'") {
        it("search for 'aa' finds 3 hits since we want non disjoint matches") {
            expect("aaaa").contains.exactly(3).regex("aa")
        }
        it("search for 'aa?' finds 4 hits since we want non disjoint matches") {
            expect("aaaa").contains.exactly(4).regex("aa?")
        }
    }
}) {
    object StringSpec : ch.tutteli.atrium.specs.integration.CharSequenceToContainRegexExpectationsSpec(
        getNameToContainRegex(),
        getAtLeastTripleString(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleString(),
        getAtMostTripleString(),
        getAtMostIgnoringCaseTripleString(),
        "[StringSpec] "
    )

    object RegexSpec : ch.tutteli.atrium.specs.integration.CharSequenceToContainRegexExpectationsSpec(
        getNameToContainRegex(),
        getAtLeastTripleRegex(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleRegex(),
        getAtMostTripleRegex(),
        getAtMostIgnoringCaseTripleString(),
        "[RegexSpec] "
    )

    companion object : CharSequenceToContainSpecBase() {

        private fun getNameToContainRegex() = "$toContain with search mode $regex"

        private fun getAtLeastTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain.$atLeast.$regex" to ::containsAtLeastString)

        private fun getAtLeastTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain.$atLeast.$regex" to ::containsAtLeastRegex)

        private fun containsAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = expect.contains.atLeast(atLeast).regex(a, *aX)

        private fun containsAtLeastRegex(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            expect.contains.atLeast(atLeast).regex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" } to
                ("$toContain.$atLeast.$ignoringCase.$regex" to ::containsAtLeastIgnoringCase)

        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1) expect.contains.ignoringCase.regex(a, *aX)
        else expect.contains.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::containsShortcutString)

        private fun getShortcutTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::containsShortcutRegex)

        private fun containsShortcutString(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a, *aX)

        private fun containsShortcutRegex(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.containsRegex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$regex" to ::containsAtMostString)

        private fun getAtMostTripleRegex() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$regex" to ::containsAtMostRegex)

        private fun containsAtMostString(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain.$ignoringCase.$atMost.$regex" to ::containsAtMostIgnoringCase)

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
