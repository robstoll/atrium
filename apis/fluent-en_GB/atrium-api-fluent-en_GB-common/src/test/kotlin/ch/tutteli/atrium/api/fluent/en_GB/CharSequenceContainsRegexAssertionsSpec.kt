package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsRegexAssertionsSpec(
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

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTriple() = Triple(
            "$contains.$atLeast.$regex",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>)
            = plant.contains.atLeast(atLeast).regex(a, *aX)

        private fun getAtLeastIgnoringCaseTriple() = Triple(
            "$contains.$atLeast.$ignoringCase.$regex",
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" },
            Companion::containsAtLeastIgnoringCase
        )

        private fun containsAtLeastIgnoringCase(plant: Expect<CharSequence>, atLeast: Int, a: String, aX: Array<out String>)
            = if(atLeast == 1) plant.contains.ignoringCase.regex(a, *aX)
              else plant.contains.ignoringCase.atLeast(atLeast).regex(a, *aX)

        private fun getShortcutTriple() = Triple(
            containsRegex,
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsShortcut
        )

        private fun containsShortcut(plant: Expect<CharSequence>, a: String, aX: Array<out String>)
            = plant.containsRegex(a, *aX)

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.$regex",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.contains.atMost(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.$regex",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Expect<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.contains.ignoringCase.atMost(atMost).regex(a, *aX)
    }
}
