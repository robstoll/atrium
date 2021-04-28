package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class IterableToContainInAnyOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(BuilderIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair()
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContain o inAny order but only elementsOf",
            listOf(1, 2),
            { input -> it toContain o inAny order but only elementsOf input }
        )

    companion object : IterableContainsSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getToContainValues

        private fun getToContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect toContain o inAny order but only elementsOf listOf(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getToContainNullableValues

        private fun getToContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect toContain o inAny order but only elementsOf sequenceOf(a, *aX)
    }
}
