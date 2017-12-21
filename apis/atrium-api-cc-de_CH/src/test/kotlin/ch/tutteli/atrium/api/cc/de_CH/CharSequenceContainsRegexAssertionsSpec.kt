package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsRegexAssertionSpec(
    AssertionVerbFactory,
    getNameContainsRegex(),
    getAtLeastTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "$contains with search mode $regex"

        private fun getAtLeastTriple() = Triple(
            "$contains.$atLeast.$regex",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: AssertionPlant<CharSequence>, atLeast: Int, a: String, aX: Array<out String>)
            = plant.enthaelt.zumindest(atLeast).regex(a, *aX)

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.$regex",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: AssertionPlant<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.enthaelt.hoechstens(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.$regex",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: AssertionPlant<CharSequence>, atMost: Int, a: String, aX: Array<out String>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.hoechstens(atMost).regex(a, *aX)
    }
}
