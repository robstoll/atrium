package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CharSequenceContainsAtLeastAssertionsSpec : Spek({
    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
        getAtLeastValuesTriple(),
        getAtLeastIgnoringCaseValuesTriple(),
        getAtLeastButAtMostValuesTriple(),
        getAtLeastBustAtMostIgnoringCaseValuesTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        CharSequenceContainsAtLeastAssertionsSpec.Companion::getErrorMsgAtLeastButAtMost,
        "◆ ", "⚬ "
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsAtLeastAssertionsSpec(
        getAtLeastElementsOfTriple(),
        getAtLeastIgnoringCaseElementsOfTriple(),
        getAtLeastButAtMostElementsOfTriple(),
        getAtLeastButAtMostIgnoringCaseElementsOfTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        CharSequenceContainsAtLeastAssertionsSpec.Companion::getErrorMsgAtLeastButAtMost,
        "◆ ", "⚬ "
    ) {})

    include(object : Spek({
        describe("elementsOf") {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect("test").contains.atLeast(1).elementsOf(emptyList())
                }.toThrow<IllegalArgumentException> { messageContains("Iterable without elements is not allowed") }
            }
        }
        describe("elementsOf ignoring case") {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect("test").contains.ignoringCase.atLeast(1).elementsOf(emptyList())
                }.toThrow<IllegalArgumentException> { messageContains("Iterable without elements is not allowed") }
            }
        }
    }) {})
}) {

    companion object : CharSequenceContainsSpecBase() {

        private val atLeastDescr = { what: String, times: String -> "$contains $what $atLeast $times" }
        internal fun getAtLeastValuesTriple() =
            atLeastDescr to ("$contains.$atLeast" to Companion::containsAtLeast)

        private fun containsAtLeast(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect.contains.atLeast(atLeast).value(a)
            else expect.contains.atLeast(atLeast).values(a, *aX)

        internal fun getAtLeastElementsOfTriple() =
            atLeastDescr to ("$contains.$atLeast" to Companion::containsAtLeastElementsOf)

        private fun containsAtLeastElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect.contains.atLeast(atLeast).elementsOf(listOf(a, *aX))

        private val atLeastIgnoringCaseDescr =
            { what: String, times: String -> "$contains $ignoringCase $what $atLeast $times" }

        private fun getAtLeastIgnoringCaseValuesTriple() =
            atLeastIgnoringCaseDescr to ("$contains.$ignoringCase.$atLeast" to Companion::containsAtLeastIgnoringCaseValues)

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
            atLeastIgnoringCaseDescr to ("$contains.$ignoringCase.$atLeast" to Companion::containsAtLeastIgnoringCaseElementsOf)

        private fun containsAtLeastIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            expect.contains.ignoringCase.atLeast(atLeast).elementsOf(listOf(a, *aX))

        private val atLeastButAtMostDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$contains $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastButAtMostElementsOfTriple() =
            atLeastButAtMostDescr to ("$contains.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostElementsOf)

        private fun containsAtLeastButAtMostElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private fun getAtLeastButAtMostValuesTriple() =
            atLeastButAtMostDescr to ("$contains.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostValues)

        private fun containsAtLeastButAtMostValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private val atLeastButAtMostIgnoringCaseDescr = { what: String, timesAtLeast: String, timesAtMost: String ->
            "$contains $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost"
        }

        private fun getAtLeastBustAtMostIgnoringCaseValuesTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$contains.$ignoringCase.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostIgnoringCaseValues)

        private fun containsAtLeastButAtMostIgnoringCaseValues(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).values(a, *aX)

        private fun getAtLeastButAtMostIgnoringCaseElementsOfTriple() =
            atLeastButAtMostIgnoringCaseDescr to ("$contains.$ignoringCase.$atLeast.$butAtMost" to Companion::containsAtLeastButAtMostIgnoringCaseElementsOf)

        private fun containsAtLeastButAtMostIgnoringCaseElementsOf(
            expect: Expect<CharSequence>,
            atLeast: Int,
            butAtMost: Int,
            a: Any,
            aX: Array<out Any>
        ) = expect.contains.ignoringCase.atLeast(atLeast).butAtMost(butAtMost).elementsOf(listOf(a, *aX))

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $atLeast($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atLeast($times).$butAtMost($times)"

        internal fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int) =
            "specifying $butAtMost($timesButAtMost) does not make sense if $atLeast($timesAtLeast) was used before"
    }
}
