package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CharSequenceToContainRegexExpectationsTest : Spek({
    include(StringSpec)
    include(RegexSpec)

    describe("context 'aaaa'") {
        it("search for 'aa' finds 3 hits since we want non-disjoint matches") {
            expect("aaaa").toContain.exactly(3).regex("aa")
        }
        it("search for 'aa?' finds 4 hits since we want non-disjoint matches") {
            expect("aaaa").toContain.exactly(4).regex("aa?")
        }
    }
}) {
    object StringSpec : ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainRegexExpectationsTest(
        getNameToContainRegex(),
        getAtLeastTripleString(),
        getAtLeastIgnoringCaseTripleString(),
        getShortcutTripleString(),
        getAtMostTripleString(),
        getAtMostIgnoringCaseTripleString(),
        "[StringSpec] "
    )

    object RegexSpec : ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainRegexExpectationsTest(
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
                ("$toContain.$atLeast.$regex" to ::toContainAtLeastString)

        private fun getAtLeastTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain.$atLeast.$regex" to ::toContainAtLeastRegex)

        private fun toContainAtLeastString(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = expect.toContain.atLeast(atLeast).regex(a, *aX)

        private fun toContainAtLeastRegex(expect: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>) =
            expect.toContain.atLeast(atLeast).matchFor(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtLeastIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" } to
                ("$toContain.$atLeast.$ignoringCase.$regex" to ::toContainAtLeastIgnoringCase)

        private fun toContainAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: String,
            aX: Array<out String>
        ) = if (atLeast == 1) expect.toContain.ignoringCase.regex(a, *aX)
        else expect.toContain.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTripleString() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::toContainShortcutString)

        private fun getShortcutTripleRegex() =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                (toContainRegex to ::toContainShortcutRegex)

        private fun toContainShortcutString(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.toContainRegex(a, *aX)

        private fun toContainShortcutRegex(expect: Expect<CharSequence>, a: String, aX: Array<out String>) =
            expect.toContainRegex(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun getAtMostTripleString() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$regex" to ::toContainAtMostString)

        private fun getAtMostTripleRegex() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$atMost.$regex" to ::toContainAtMostRegex)

        private fun toContainAtMostString(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.toContain.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTripleString() =
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" } to
                ("$toContain.$ignoringCase.$atMost.$regex" to ::toContainAtMostIgnoringCase)

        private fun toContainAtMostRegex(expect: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>) =
            expect.toContain.atMost(atMost).matchFor(a.toRegex(), *aX.map { it.toRegex() }.toTypedArray())

        private fun toContainAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atMost: Int,
            a: String,
            aX: Array<out String>
        ) = expect.toContain.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
