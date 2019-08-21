package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsNotOrAtMostAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsNotOrAtMostAssertionsSpec(
        AssertionVerbFactory,
        getNotOrAtMostTriple(),
        getNotOrAtMostIgnoringCaseTriple(),
        getContainsNotPair(),
        "◆ ", "⚬ "
    ) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$contains $what $notOrAtMost $times" } to
                ("$contains.$notOrAtMost" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(plant: Expect<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>) =
            plant.contains.notOrAtMost(atMost).values(a, *aX)

        private fun getNotOrAtMostIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $notOrAtMost $times" } to
                ("$contains.$ignoringCase.$notOrAtMost" to Companion::containsNotOrAtMostIgnoringCase)

        private fun containsNotOrAtMostIgnoringCase(
            plant: Expect<CharSequence>,
            atMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = plant.contains.ignoringCase.notOrAtMost(atMost).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $notOrAtMost($times)"

    }
}
