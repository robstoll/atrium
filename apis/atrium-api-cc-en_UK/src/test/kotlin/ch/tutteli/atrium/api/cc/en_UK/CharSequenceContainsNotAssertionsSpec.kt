@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsNotAssertionsSpec(
    AssertionVerbFactory,
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() = Triple(
            containsNot,
            { what: String -> "$containsNot $what" },
            Companion::containsNotFun
        )

        private fun containsNotFun(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.values(a, *aX)

        private fun getContainsNotIgnoringCaseTriple() = Triple(
            "$containsNot.$ignoringCase",
            { what: String -> "$containsNot $ignoringCase $what" },
            Companion::containsNotIgnoringCase
        )

        private fun containsNotIgnoringCase(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.ignoringCase.values(a, *aX)
    }
}
