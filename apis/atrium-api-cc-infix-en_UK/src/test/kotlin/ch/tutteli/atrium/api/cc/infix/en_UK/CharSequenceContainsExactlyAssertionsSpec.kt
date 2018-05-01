package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsExactlyAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsExactlyAssertionSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getExactlyIgnoringCaseTriple(),
    getContainsNotPair()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$toContain $exactly",
            { what: String, times: String -> "$toContain $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant to contain exactly exactly the Values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $exactly",
            { what: String, times: String -> "$toContain $ignoringCase $what $exactly $times" },
            Companion::containsExactlyIgnoringCase
        )

        private fun containsExactlyIgnoringCase(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant to contain ignoring case exactly exactly the Values(a, *aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$exactly $times`"

    }
}
