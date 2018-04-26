package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsRegexAssertionSpec(
    AssertionVerbFactory,
    getNameContainsRegex(),
    getAtLeastTriple(),
    getShortcutTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$toContain with search mode $regex"

        private fun getAtLeastTriple() = Triple(
            "$toContain $atLeast $regex",
            { what: String, times: String -> "$toContain $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: String, aX: Array<out String>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain atLeast atLeast regex a
            } else {
                plant to contain atLeast atLeast the RegexPatterns(a, *aX)
            }
        }

        private fun getShortcutTriple() = Triple(
            containsRegex,
            { what: String, times: String -> "$toContain $what $atLeast $times" },
            Companion::containsShortcut
        )

        private fun containsShortcut(plant: Assert<CharSequence>, a: String, aX: Array<out String>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant containsRegex a
            } else {
                plant contains RegexPatterns(a, *aX)
            }
        }

        private fun getAtMostTriple() = Triple(
            "$toContain $atMost $regex",
            { what: String, times: String -> "$toContain $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant to contain atMost atMost the RegexPatterns(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atMost $regex",
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain ignoring case atMost atMost regex a
            } else {
                plant to contain ignoring case atMost atMost the RegexPatterns(a, *aX)
            }
        }
    }
}
