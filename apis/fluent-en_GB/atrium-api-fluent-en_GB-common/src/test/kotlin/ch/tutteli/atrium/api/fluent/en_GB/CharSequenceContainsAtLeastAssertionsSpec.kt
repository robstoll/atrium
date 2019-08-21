package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceContainsAtLeastAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
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

        private fun containsAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect.contains.atLeast(atLeast).value(a)
            else expect.contains.atLeast(atLeast).values(a, *aX)

        private fun getAtLeastIgnoringCaseTriple() =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" } to
                ("$contains.$ignoringCase.$atLeast" to Companion::containsAtLeastIgnoringCase)


        private fun containsAtLeastIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect.contains.ignoringCase.value(a)
                else expect.contains.ignoringCase.atLeast(atLeast).value(a)
            } else {
                if (atLeast == 1) expect.contains.ignoringCase.values(a, *aX)
                else expect.contains.ignoringCase.atLeast(atLeast).values(a, *aX)
            }

        private fun getAtLeastButAtMostTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$contains.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMost)

        private fun containsAtLeastButAtMost(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getAtLeastBustAtMostIgnoringCaseTriple() =
            { what: String, timesAtLeast: String, timesAtMost: String -> "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost" } to
                ("$contains.$ignoringCase.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostIgnoringCase)

        private fun containsAtLeastButAtMostIgnoringCase(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
