package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsAtMostAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsAtMostAssertionsSpec(
    AssertionVerbFactory,
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    "* ", "- "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.hoechstens(atMost).werte(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.hoechstens(atMost).werte(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atMost($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atMost($times); $atMost($times) defines implicitly $atLeast($times) as well"
    }
}
