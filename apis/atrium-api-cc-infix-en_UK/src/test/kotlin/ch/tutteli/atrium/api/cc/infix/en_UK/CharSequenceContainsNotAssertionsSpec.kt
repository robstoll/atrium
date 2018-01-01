package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() = Triple(
            notToContain,
            { what: String -> "$notToContain $what" },
            Companion::containsNotFun
        )

        private fun containsNotFun(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private fun getContainsNotIgnoringCaseTriple() = Triple(
            "$notToContain $ignoringCase",
            { what: String -> "$notToContain $ignoringCase $what" },
            Companion::containsNotIgnoringCase
        )

        private fun containsNotIgnoringCase(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant notTo contain ignoring case value a
            } else {
                plant notTo contain ignoring case the Values(a, *aX)
            }
        }
    }
}
