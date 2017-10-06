package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsNotOrAtMostAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsNotOrAtMostAssertionSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getNotOrAtMostIgnoringCasePair(),
    getContainsNotPair()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$contains.$notOrAtMost",
            { what: String, times: String -> "$contains $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.notOrAtMost(atMost).values(a, *aX)

        private fun getNotOrAtMostIgnoringCasePair() = Pair(
            { what: String, times: String -> "$contains $ignoringCase $what $notOrAtMost $times" },
            Companion::containsNotOrAtMostIgnoringCase
        )

        private fun containsNotOrAtMostIgnoringCase(plant: IAssertionPlant<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.ignoringCase.notOrAtMost(atMost).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $notOrAtMost($times)"

    }
}
