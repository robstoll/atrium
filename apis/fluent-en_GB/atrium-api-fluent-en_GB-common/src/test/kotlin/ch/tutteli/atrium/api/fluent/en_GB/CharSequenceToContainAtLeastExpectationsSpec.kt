package ch.tutteli.atrium.api.fluent.en_GB

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
        "$toContain.$atLeast(1).$elementsOf",
        "hello",
        { input -> contains.atLeast(1).elementsOf(input) }
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<String>(
        "$toContain.$ignoringCase.$atLeast(1).$elementsOf",
        "hello",
        { input -> contains.ignoringCase.atLeast(1).elementsOf(input) }
    ) {})


    include(object : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<String>(
        "$toContain.$ignoringCase.$elementsOf",
        "hello",
        { input -> contains.ignoringCase.elementsOf(input) }
    ) {})
}) {

    companion object : CharSequenceToContainSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$toContain $what $atLeast $times" }
        internal fun getAtLeastValuesTriple() =
            atLeastDescr to ("$toContain.$atLeast.$value/$values" to Companion::containsAtLeast)

        private fun containsAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect.contains.atLeast(atLeast).value(a)
            else expect.contains.atLeast(atLeast).values(a, *aX)

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$toContain.$atLeast.$elementsOf" to Companion::containsAtLeastElementsOf)

        private fun containsAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect.contains.atLeast(atLeast).elementsOf(listOf(a, *aX))

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseValuesTriple() =
            atLeastIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$value/$values" to Companion::containsAtLeastIgnoringCaseValues)

        private fun containsAtLeastIgnoringCaseValues(
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

        private fun getAtLeastIgnoringCaseElementsOfTriple() =
            atLeastIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$elementsOf" to Companion::containsAtLeastIgnoringCaseElementsOf)

        private fun containsAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) {
                if (atLeast == 1) expect.contains.ignoringCase.elementsOf(listOf(a))
                else expect.contains.ignoringCase.atLeast(atLeast).elementsOf(listOf(a))
            } else {
                if (atLeast == 1) expect.contains.ignoringCase.elementsOf(listOf(a, *aX))
                else expect.contains.ignoringCase.atLeast(atLeast).elementsOf(listOf(a, *aX))
            }

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$toContain.$atLeast.$butAtMost.$elementsOf" to Companion::containsAtLeastButAtMostElementsOf)

        private fun containsAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private fun getAtLeastButAtMostValuesTriple() =
            atLeastButAtMostDescr to ("$toContain.$atLeast.$butAtMost.$value/$values" to Companion::containsAtLeastButAtMostValues)

        private fun containsAtLeastButAtMostValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastBustAtMostIgnoringCaseValuesTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$butAtMost.$value/$values" to Companion::containsAtLeastButAtMostIgnoringCaseValues)

        private fun containsAtLeastButAtMostIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$toContain.$ignoringCase.$atLeast.$butAtMost.$elementsOf" to Companion::containsAtLeastButAtMostIgnoringCaseElementsOf)

        private fun containsAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private fun getNotToContainPair() = toContainNot to ::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $toContainNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
