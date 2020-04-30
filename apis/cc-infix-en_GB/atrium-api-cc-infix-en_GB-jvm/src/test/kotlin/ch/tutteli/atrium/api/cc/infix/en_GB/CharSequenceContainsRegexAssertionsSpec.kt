// TODO remove file with 1.0.0
//@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.atLeast
import ch.tutteli.atrium.api.infix.en_GB.atMost
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsRegexAssertionsSpec(
    AssertionVerbFactory,
    getNameContainsRegex(),
    getAtLeastTriple(),
    getAtLeastIgnoringCaseTriple(),
    getShortcutTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    "◆ ", "⚬ "
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
                (plant to contain) atLeast atLeast regex a
            } else {
                val patterns = RegexPatterns(a, *aX)
                (plant to contain).atLeast(atLeast).the(regexPatterns(patterns.expected, *patterns.otherExpected))
                    .asAssert()
            }
        }

        private fun getAtLeastIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atLeast $regex",
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" },
            Companion::containsAtLeastIgnoringCase
        )

        private fun containsAtLeastIgnoringCase(plant: Assert<CharSequence>, atLeast: Int, a: String, aX: Array<out String>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                if(atLeast == 1) plant to contain ignoring case regex a
                else plant to contain ignoring case atLeast atLeast regex a
            } else {
                if(atLeast == 1) {
                    val patterns = RegexPatterns(a, *aX)
                    (plant to contain ignoring case).the(regexPatterns(patterns.expected, *patterns.otherExpected)).asAssert()
                }
                else {
                    val patterns = RegexPatterns(a, *aX)
                    (plant to contain ignoring case atLeast atLeast).the(
                        regexPatterns(
                            patterns.expected,
                            *patterns.otherExpected
                        )
                    ).asAssert()
                }
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
                val patterns = RegexPatterns(a, *aX)
                plant.asExpect().contains(regexPatterns(patterns.expected, *patterns.otherExpected)).asAssert()

            }
        }

        private fun getAtMostTriple() = Triple(
            "$toContain $atMost $regex",
            { what: String, times: String -> "$toContain $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>): AssertionPlant<CharSequence> {
            val patterns = RegexPatterns(a, *aX)
            return (plant to contain atMost atMost).the(regexPatterns(patterns.expected, *patterns.otherExpected))
                .asAssert()
        }

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atMost $regex",
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain ignoring case atMost atMost regex a
            } else {
                val patterns = RegexPatterns(a, *aX)
                (plant to contain ignoring case).atMost(atMost)
                    .the(regexPatterns(patterns.expected, *patterns.otherExpected)).asAssert()
            }
        }
    }
}
