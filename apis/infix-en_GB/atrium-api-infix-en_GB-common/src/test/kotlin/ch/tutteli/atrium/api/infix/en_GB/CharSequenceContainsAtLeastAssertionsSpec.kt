package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class CharSequenceContainsAtLeastAssertionsSpec : Spek({
    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
        getAtLeastValuesTriple(),
        getAtLeastIgnoringCaseValuesTriple(),
        getAtLeastButAtMostValuesTriple(),
        getAtLeastBustAtMostIgnoringCaseValuesTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        CharSequenceContainsAtLeastAssertionsSpec.Companion::getErrorMsgAtLeastButAtMost,
        "* ", "- "
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
        getAtLeastElementsOfTriple(),
        getAtLeastIgnoringCaseElementsOfTriple(),
        getAtLeastButAtMostElementsOfTriple(),
        getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        CharSequenceContainsAtLeastAssertionsSpec.Companion::getErrorMsgAtLeastButAtMost,
        "* ", "- "
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeSpec<String>(
        "contains o atLeast 1 elementsOf",
        "hello",
        { input -> it contains o atLeast 1 elementsOf input },
        { input -> it contains o atLeast 1 elementsOf input }
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeSpec<String>(
        " contains o ignoring case atLeast 1 elementsOf",
        "hello",
        { input -> it contains o ignoring case atLeast 1 elementsOf input },
        { input -> it contains o ignoring case atLeast 1 elementsOf input }
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeSpec<String>(
        "contains o ignoring case elementsOf",
        "hello",
        { input -> it contains o ignoring case elementsOf input },
        { input -> it contains o ignoring case elementsOf input }
    ) {})
}) {

    companion object : CharSequenceContainsSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$contains $what $atLeast $times" }
        internal fun getAtLeastValuesTriple() =
            atLeastDescr to ("$contains o $atLeast" to Companion::containsAtLeast)

        private fun containsAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect contains o atLeast atLeast value a
            else expect contains o atLeast atLeast the values(a, *aX)

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$contains o $atLeast" to Companion::containsAtLeastElementsOf)

        private fun containsAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect contains o atLeast atLeast elementsOf listOf(a, *aX)

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$contains o $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseValuesTriple() =
            atLeastIgnoringCaseDescr to ("$contains o $ignoringCase $atLeast" to Companion::containsAtLeastIgnoringCaseValues)

        private fun containsAtLeastIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect contains o ignoring case value a
                else expect contains o ignoring case atLeast atLeast value a
            } else {
                if (atLeast == 1) expect contains o ignoring case the values(a, *aX)
                else expect contains o ignoring case atLeast atLeast the values(a, *aX)
            }

        private fun getAtLeastIgnoringCaseElementsOfTriple() =
            atLeastIgnoringCaseDescr to ("$contains o $ignoringCase $atLeast" to Companion::containsAtLeastIgnoringCaseElementsOf)

        private fun containsAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect contains o ignoring case elementsOf listOf(a)
                else expect contains o ignoring case atLeast atLeast elementsOf listOf(a)
            } else {
                if (atLeast == 1) expect contains o ignoring case elementsOf listOf(a, *aX)
                else expect contains o ignoring case atLeast atLeast elementsOf listOf(a, *aX)
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$contains o $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$contains o $atLeast o $butAtMost" to Companion::containsAtLeastButAtMostElementsOf)

        private fun containsAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect contains o atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private fun getAtLeastButAtMostValuesTriple() =
            atLeastButAtMostDescr to ("$contains o $atLeast o $butAtMost" to Companion::containsAtLeastButAtMostValues)

        private fun containsAtLeastButAtMostValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect contains o atLeast atLeast butAtMost butAtMost value a
            else expect contains o atLeast atLeast butAtMost butAtMost the values(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastBustAtMostIgnoringCaseValuesTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$contains o $ignoringCase $atLeast $butAtMost" to Companion::containsAtLeastButAtMostIgnoringCaseValues)

        private fun containsAtLeastButAtMostIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect contains o ignoring case atLeast atLeast butAtMost butAtMost value a
            else expect contains o ignoring case atLeast atLeast butAtMost butAtMost the values(a, *aX)

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$contains o $ignoringCase $atLeast $butAtMost" to Companion::containsAtLeastButAtMostIgnoringCaseElementsOf)

        private fun containsAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect contains o ignoring case atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use `$containsNotValues` instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
