package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.iterableLikeTypes
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class IterableContainsInAnyOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)

    // TODO remove with 1.0.0
    describe("contains o inAny order but only elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inAny order but only elementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)) contains o inAny order but only elementsOf input
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    describe("contains o inAny order but only elementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inAny order but only elementsOf "test"
            }.toThrow<IllegalArgumentException> { it messageContains ("toVarArg accepts arguments of types Iterable, Sequence, Array") }
        }
    }
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getContainsValues

        private fun getContainsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect contains o inAny order but only elementsOf listOf(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getContainsNullableValues

        private fun getContainsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect contains o inAny order but only elementsOf listOf(a, *aX)
    }
}
