package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class IterableContainsInAnyOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)

    describe("elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inAny order but only elementsOf listOf()
            }.toThrow<IllegalArgumentException>()
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
