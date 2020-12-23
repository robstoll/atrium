package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderOnlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyValues

        private fun containsInAnyOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order but only value a
            else expect contains o inAny order but only the values(a, *aX)


        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyNullableValues

        private fun containsInAnyOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inAny order but only value a
            else expect contains o inAny order but only the values(a, *aX)

    }
}
