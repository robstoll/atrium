package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderOnlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyValues

        private fun containsInAnyOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.value(a)
            else expect.contains.inAnyOrder.only.values(a, *aX)


        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyNullableValues

        private fun containsInAnyOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.value(a)
            else expect.contains.inAnyOrder.only.values(a, *aX)

    }
}
