package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderOnlyEntriesSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderOnlyEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the Entries(a, *aX)
            }
        }

    }
}
