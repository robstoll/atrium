package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.*
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsRegexAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsRegexAssertionSpec(
    AssertionVerbFactory,
    getNameContainsRegex(),
    getAtLeastPair(),
    getAtMostTriple(),
    getAtMostIgnoringCasePair(),
    Companion::containsExactly
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsRegex() = "enthaelt with search mode $regex"

        private fun getAtLeastPair() = Pair(
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: IAssertionPlant<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.zumindest(atLeast).regex(a, *aX)

        private fun getAtMostTriple() = Pair(
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.hoechstens(atMost).regex(a, *aX)

        private fun getAtMostIgnoringCasePair() = Pair(
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.hoechstens(atMost).regex(a, *aX)

        private fun containsExactly(plant: IAssertionPlant<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.genau(exactly).regex(a, *aX)
    }
}
