package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class IterableContainsInOrderOnlyGroupedNullableSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedNullableSpec(
    AssertionVerbFactory,
    getContainsInOrderOnlyGroupedNullableEntriesPair(),
    "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ "
) {
    companion object : IterableContainsSpecBase() {

        fun getContainsInOrderOnlyGroupedNullableEntriesPair() =
            "$toContain $inOrder $butOnly $inOrderOnlyEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedNullableEntriesPair

        private fun containsInOrderOnlyGroupedNullableEntriesPair(
            plant: Assert<Iterable<Double?>>,
            a1: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            a2: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            aX: Array<out GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            return plant to contain inGiven order and only grouped entries within group inAny Order(a1, a2, *aX)
        }
    }
}
