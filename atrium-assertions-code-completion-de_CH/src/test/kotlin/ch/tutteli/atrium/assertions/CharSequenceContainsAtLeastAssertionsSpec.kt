package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.charsequence.contains.builders.zumindest
import ch.tutteli.atrium.assertions.charsequence.contains.builders.aberHoechstens
import ch.tutteli.atrium.assertions.charsequence.contains.builders.ignoriereGrossKleinschreibung
import ch.tutteli.atrium.assertions.charsequence.contains.builders.werte
import ch.tutteli.atrium.enthaelt
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsAtLeastAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsAtLeastAssertionSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    getAtLeastIgnoringCasePair(),
    getAtLeastButAtMostTriple(),
    getAtLeastBustAtMostIgnoringCasePair(),
    getContainsNotPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost
) {

    companion object : CharSequenceContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$contains.$atLeast",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: IAssertionPlant<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.zumindest(atLeast).werte(a, *aX)

        private fun getAtLeastIgnoringCasePair() = Pair(
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" },
            Companion::containsAtLeastIgnoringCase
        )

        private fun containsAtLeastIgnoringCase(plant: IAssertionPlant<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.zumindest(atLeast).werte(a, *aX)

        private fun getAtLeastButAtMostTriple() = Triple(
            "$contains.$atLeast.$butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMost
        )

        private fun containsAtLeastButAtMost(plant: IAssertionPlant<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.zumindest(atLeast).aberHoechstens(butAtMost).werte(a, *aX)

        private fun getAtLeastBustAtMostIgnoringCasePair() = Pair(
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMostIgnoringCase
        )

        private fun containsAtLeastButAtMostIgnoringCase(plant: IAssertionPlant<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.zumindest(atLeast).aberHoechstens(butAtMost).werte(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
            = "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
