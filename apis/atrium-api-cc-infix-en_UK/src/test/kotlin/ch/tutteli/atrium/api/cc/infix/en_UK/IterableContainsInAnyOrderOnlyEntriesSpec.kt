package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class IterableContainsInAnyOrderOnlyEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the Entries(a, *aX)
            }
        }

    }
}
