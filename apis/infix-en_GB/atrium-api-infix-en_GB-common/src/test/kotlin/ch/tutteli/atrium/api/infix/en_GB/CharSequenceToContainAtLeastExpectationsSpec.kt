package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class CharSequenceToContainAtLeastExpectationsSpec : Spek({
    include(object : ch.tutteli.atrium.specs.integration.CharSequenceToContainAtLeastExpectationsSpec(
        getAtLeastValuesTriple(),
        getAtLeastIgnoringCaseValuesTriple(),
        getAtLeastButAtMostValuesTriple(),
        getAtLeastBustAtMostIgnoringCaseValuesTriple(),
        getNotToContainPair(),
        getExactlyPair(),
        CharSequenceToContainAtLeastExpectationsSpec.Companion::getErrorMsgAtLeastButAtMost
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceToContainAtLeastExpectationsSpec(
        getAtLeastElementsOfTriple(),
        getAtLeastIgnoringCaseElementsOfTriple(),
        getAtLeastButAtMostElementsOfTriple(),
        getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
        getNotToContainPair(),
        getExactlyPair(),
        CharSequenceToContainAtLeastExpectationsSpec.Companion::getErrorMsgAtLeastButAtMost
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<String>(
        "toContain o atLeast 1 elementsOf",
        "hello",
        { input -> it toContain o atLeast 1 elementsOf input }
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<String>(
        " toContain o ignoring case atLeast 1 elementsOf",
        "hello",
        { input -> it toContain o ignoring case atLeast 1 elementsOf input }
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<String>(
        "toContain o ignoring case elementsOf",
        "hello",
        { input -> it toContain o ignoring case elementsOf input }
    ) {})
}) {

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }
        internal fun getAtLeastValuesTriple() =
            atLeastDescr to ("$toContain o $atLeast $value/$values" to Companion::toContainAtLeast)

        private fun toContainAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atLeast atLeast value a
            else expect toContain o atLeast atLeast the values(a, *aX)

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$toContain o $atLeast $elementsOf" to Companion::toContainAtLeastElementsOf)

        private fun toContainAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect toContain o atLeast atLeast elementsOf listOf(a, *aX)

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$toContain o $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseValuesTriple() =
            atLeastIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $value/$values" to Companion::toContainAtLeastIgnoringCaseValues)

        private fun toContainAtLeastIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case value a
                else expect toContain o ignoring case atLeast atLeast value a
            } else {
                if (atLeast == 1) expect toContain o ignoring case the values(a, *aX)
                else expect toContain o ignoring case atLeast atLeast the values(a, *aX)
            }

        private fun getAtLeastIgnoringCaseElementsOfTriple() =
            atLeastIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $elementsOf" to Companion::toContainAtLeastIgnoringCaseElementsOf)

        private fun toContainAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect toContain o ignoring case elementsOf listOf(a)
                else expect toContain o ignoring case atLeast atLeast elementsOf listOf(a)
            } else {
                if (atLeast == 1) expect toContain o ignoring case elementsOf listOf(a, *aX)
                else expect toContain o ignoring case atLeast atLeast elementsOf listOf(a, *aX)
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain o $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$toContain o $atLeast o $butAtMost $elementsOf" to Companion::toContainAtLeastButAtMostElementsOf)

        private fun toContainAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect toContain o atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private fun getAtLeastButAtMostValuesTriple() =
            atLeastButAtMostDescr to ("$toContain o $atLeast o $butAtMost $value/$values" to Companion::toContainAtLeastButAtMostValues)

        private fun toContainAtLeastButAtMostValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect toContain o atLeast atLeast butAtMost butAtMost value a
            else expect toContain o atLeast atLeast butAtMost butAtMost the values(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastBustAtMostIgnoringCaseValuesTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $butAtMost $value/$values" to Companion::toContainAtLeastButAtMostIgnoringCaseValues)

        private fun toContainAtLeastButAtMostIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) =
            if (aX.isEmpty()) expect toContain o ignoring case atLeast atLeast butAtMost butAtMost value a
            else expect toContain o ignoring case atLeast atLeast butAtMost butAtMost the values(a, *aX)

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain o $ignoringCase $atLeast $butAtMost $elementsOf" to Companion::toContainAtLeastButAtMostIgnoringCaseElementsOf)

        private fun toContainAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect toContain o ignoring case atLeast atLeast butAtMost butAtMost elementsOf listOf(a, *aX)

        private fun getNotToContainPair() = notToContainValues to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContainValues` instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
