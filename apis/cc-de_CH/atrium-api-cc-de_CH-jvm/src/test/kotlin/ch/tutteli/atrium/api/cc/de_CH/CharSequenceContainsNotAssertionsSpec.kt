package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsNotAssertionsSpec(
    AssertionVerbFactory,
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple(),
    "* ", "- "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() = Triple(
            containsNot,
            { what: String -> "$containsNot $what" },
            Companion::containsNotFun
        )

        private fun containsNotFun(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaeltNicht.werte(a, *aX)

        private fun getContainsNotIgnoringCaseTriple() = Triple(
            "$containsNot.$ignoringCase",
            { what: String -> "$containsNot $ignoringCase $what" },
            Companion::containsNotIgnoringCase
        )

        private fun containsNotIgnoringCase(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaeltNicht.ignoriereGrossKleinschreibung.werte(a, *aX)
    }
}
