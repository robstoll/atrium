package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsAtLeastAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
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

        internal fun getAtLeastTriple() =
            { what: String, times: String -> "$contains $what $atLeast $times" } to
                ("$contains.$atLeast" to Companion::containsAtLeast)

        private fun containsAtLeast(plant: Expect<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Expect<CharSequence> {
            return if (aX.isEmpty()) {
                plant.contains.atLeast(atLeast).value(a)
            } else {
                plant.contains.atLeast(atLeast).values(a, *aX)
            }
        }

        private fun getAtLeastIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$ignoringCase.$atLeast" to Companion::containsAtLeastIgnoringCase)


        private fun containsAtLeastIgnoringCase(plant: Expect<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Expect<CharSequence> {
            return if (aX.isEmpty()) {
                if(atLeast == 1) plant.contains.ignoringCase.value(a)
                else plant.contains.ignoringCase.atLeast(atLeast).value(a)
            } else {
                if(atLeast == 1) plant.contains.ignoringCase.values(a, *aX)
                else plant.contains.ignoringCase.atLeast(atLeast).values(a, *aX)
            }
        }

        private fun getAtLeastButAtMostTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$contains.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMost)

        private fun containsAtLeastButAtMost(plant: Expect<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant.contains.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getAtLeastBustAtMostIgnoringCaseTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$contains.$ignoringCase.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostIgnoringCase)

        private fun containsAtLeastButAtMostIgnoringCase(plant: Expect<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
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
