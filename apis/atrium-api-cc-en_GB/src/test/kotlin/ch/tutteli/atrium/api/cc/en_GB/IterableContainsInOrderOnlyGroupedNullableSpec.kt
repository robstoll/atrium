package ch.tutteli.atrium.api.cc.en_GB

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
            "$contains.$inOrder.$only.$inOrderOnlyEntries.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedNullableEntriesPair

        private fun containsInOrderOnlyGroupedNullableEntriesPair(
            plant: Assert<Iterable<Double?>>,
            a1: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            a2: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            aX: Array<out GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }
    }
}
