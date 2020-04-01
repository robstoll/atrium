package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderOnlyEntriesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyEntriesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyEntries

        private fun containsInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order but only entry a
            else expect contains o inAny order but only the Entries(a, *aX)


        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntries

        private fun containsInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inAny order but only entry a
            else expect contains o inAny order but only the Entries(a, *aX)

    }
}
