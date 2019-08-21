package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderOnlyEntriesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyEntries

        private fun containsInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)


        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntries

        private fun containsInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)

    }
}
