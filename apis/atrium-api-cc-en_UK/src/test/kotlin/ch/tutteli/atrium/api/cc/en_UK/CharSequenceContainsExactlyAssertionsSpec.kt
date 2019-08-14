@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsExactlyAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsExactlyAssertionsSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getExactlyIgnoringCaseTriple(),
    getContainsNotPair(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$contains.$exactly",
            { what: String, times: String -> "$contains $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.contains.exactly(exactly).values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$exactly",
            { what: String, times: String -> "$contains $ignoringCase $what $exactly $times" },
            Companion::containsExactlyIgnoringCase
        )

        private fun containsExactlyIgnoringCase(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.contains.ignoringCase.exactly(exactly).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $exactly($times)"

    }
}
