// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsAtMostAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsAtMostAssertionsSpec(
    AssertionVerbFactory,
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getAtMostTriple() = Triple(
            "$toContain $atMost",
            { what: String, times: String -> "$toContain $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain atMost atMost the Values(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atMost",
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain ignoring case atMost atMost the Values(a, *aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int)
            = "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }
}
