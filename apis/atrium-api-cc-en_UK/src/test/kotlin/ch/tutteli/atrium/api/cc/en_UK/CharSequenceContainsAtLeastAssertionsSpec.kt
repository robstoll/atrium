@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CharSequenceContainsAtLeastAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsAtLeastAssertionsSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    getAtLeastIgnoringCaseTriple(),
    getAtLeastButAtMostTriple(),
    getAtLeastBustAtMostIgnoringCaseTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost,
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$contains.$atLeast",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant.contains.atLeast(atLeast).value(a)
            } else {
                plant.contains.atLeast(atLeast).values(a, *aX)
            }
        }

        private fun getAtLeastIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atLeast",
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" },
            Companion::containsAtLeastIgnoringCase
        )

        private fun containsAtLeastIgnoringCase(plant: Assert<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant.contains.ignoringCase.atLeast(atLeast).value(a)
            } else {
                plant.contains.ignoringCase.atLeast(atLeast).values(a, *aX)
            }
        }

        private fun getAtLeastButAtMostTriple() = Triple(
            "$contains.$atLeast.$butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMost
        )

        private fun containsAtLeastButAtMost(plant: Assert<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getAtLeastBustAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atLeast.$butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMostIgnoringCase
        )

        private fun containsAtLeastButAtMostIgnoringCase(plant: Assert<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
            = "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
