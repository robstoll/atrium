package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsNotOrAtMostAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsNotOrAtMostAssertionSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getNotOrAtMostIgnoringCaseTriple(),
    getContainsNotPair()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$toContain $notOrAtMost",
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain notOrAtMost atMost the Values(a, aX)

        private fun getNotOrAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $notOrAtMost",
            { what: String, times: String -> "$toContain $ignoringCase $what $notOrAtMost $times" },
            Companion::containsNotOrAtMostIgnoringCase
        )

        private fun containsNotOrAtMostIgnoringCase(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain ignoring case notOrAtMost atMost the Values(a, aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$notOrAtMost $times`"
    }
}
