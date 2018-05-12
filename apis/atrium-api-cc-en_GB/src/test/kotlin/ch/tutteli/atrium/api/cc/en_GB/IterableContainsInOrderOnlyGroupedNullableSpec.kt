package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
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
            a1: Group<(Assert<Double>.() -> Unit)?>,
            a2: Group<(Assert<Double>.() -> Unit)?>,
            aX: Array<out Group<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }
    }
}
