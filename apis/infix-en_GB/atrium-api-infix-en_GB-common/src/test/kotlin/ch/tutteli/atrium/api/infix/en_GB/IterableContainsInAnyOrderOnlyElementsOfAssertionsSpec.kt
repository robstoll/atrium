package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class IterableContainsInAnyOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
    include(BuilderIterableLikeSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» "
    )

    object BuilderIterableLikeSpec : ch.tutteli.atrium.specs.integration.IterableLikeSpec<List<Int>>(
        "contains o inAny order but only elementsOf",
        listOf(1, 2),
        { input -> it contains o inAny order but only elementsOf input },
        { input -> it contains o inAny order but only elementsOf input }
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
