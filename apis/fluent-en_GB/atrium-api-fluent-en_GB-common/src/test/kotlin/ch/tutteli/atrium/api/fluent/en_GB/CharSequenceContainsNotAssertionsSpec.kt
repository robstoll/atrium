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

        private fun getContainsNotTriple() =
            { what: String -> "$containsNot $what" } to
                (containsNot to Companion::containsNotFun)

        private fun containsNotFun(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.values(a, *aX)

        private fun getContainsNotIgnoringCaseTriple() =
            { what: String -> "$containsNot $ignoringCase $what" } to
                ("$containsNot.$ignoringCase" to Companion::containsNotIgnoringCase)

        private fun containsNotIgnoringCase(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.containsNot.ignoringCase.values(a, *aX)
    }
}
