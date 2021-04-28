package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

class IterableToContainInAnyOrderOnlyEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyEntriesExpectationsSpec(
        getContainsPair(),
        getContainsNullablePair()
    ) {

    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyEntries

        private fun toContainInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order but only entry a
            else expect toContain o inAny order but only the entries(a, *aX)


        fun getContainsNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyNullableEntries

        private fun toContainInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain o inAny order but only entry a
            else expect toContain o inAny order but only the entries(a, *aX)

    }
}
