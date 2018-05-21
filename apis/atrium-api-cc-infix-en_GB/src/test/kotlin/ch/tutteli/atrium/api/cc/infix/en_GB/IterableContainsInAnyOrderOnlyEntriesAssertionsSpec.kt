package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderOnlyEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderOnlyEntriesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNullablePair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyEntries

        private fun containsInAnyOrderOnlyEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the Entries(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyEntries nullable" to Companion::containsInAnyOrderOnlyNullableEntries

        private fun containsInAnyOrderOnlyNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the NullableEntries(a, *aX)
            }
        }
    }
}
