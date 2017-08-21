package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.charsequence.contains.builders.*
import ch.tutteli.atrium.enthaelt
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsExactlyAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsExactlyAssertionSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getExactlyIgnoringCasePair(),
    getContainsNotPair()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$contains.$exactly",
            { what: String, times: String -> "$contains $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: IAssertionPlant<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.genau(exactly).werte(a, *aX)

        private fun getExactlyIgnoringCasePair() = Pair(
            { what: String, times: String -> "$contains $ignoringCase $what $exactly $times" },
            Companion::containsExactlyIgnoringCase
        )

        private fun containsExactlyIgnoringCase(plant: IAssertionPlant<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.genau(exactly).werte(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $exactly($times)"

    }
}
