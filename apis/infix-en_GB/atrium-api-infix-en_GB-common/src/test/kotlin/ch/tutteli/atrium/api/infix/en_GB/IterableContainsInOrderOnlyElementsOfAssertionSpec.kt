package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class IterableContainsInOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)

    describe("contains o inGiven order and only elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inGiven order and only elementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inOrder $andOnly $inOrderElementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect contains o inGiven order and only elementsOf listOf(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inOrder $andOnly $inOrderElementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect contains o inGiven order and only elementsOf listOf(a, *aX)
    }
}

