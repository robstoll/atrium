package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsNotAssertionsSpec(
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

        private fun containsNotFun(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.values(a, *aX)

        private fun getContainsNotIgnoringCaseTriple() = Triple(
            "$containsNot.$ignoringCase",
            { what: String -> "$containsNot $ignoringCase $what" },
            Companion::containsNotIgnoringCase
        )

        private fun containsNotIgnoringCase(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.ignoringCase.values(a, *aX)
    }
}
