@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsRegexAssertionsSpec(
    AssertionVerbFactory,
    getNameContainsRegex(),
    getAtLeastTriple(),
    getShortcutTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTriple() = Triple(
            "$contains.$atLeast.$regex",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: String, aX: Array<out String>)
            = plant.contains.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTriple() = Triple(
            containsRegex,
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsShortcut
        )

        private fun containsShortcut(plant: Assert<CharSequence>, a: String, aX: Array<out String>)
            = plant.containsRegex(a, *aX)

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.$regex",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.$regex",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.contains.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
