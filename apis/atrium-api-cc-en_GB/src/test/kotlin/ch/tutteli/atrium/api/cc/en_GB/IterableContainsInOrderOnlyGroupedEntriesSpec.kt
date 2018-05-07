package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group

class IterableContainsInOrderOnlyGroupedEntriesSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrder

        private fun containsInOrderOnlyGroupedInAnyOrder(
            plant: Assert<Iterable<Double>>,
            a1: Group<(Assert<Double>.() -> Unit)?>,
            a2: Group<(Assert<Double>.() -> Unit)?>,
            aX: Array<out Group<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out (Assert<Double>.() -> Unit)?>): Group<(Assert<Double>.() -> Unit)?> {
            return when(groups.size){
                0 -> object: Group<(Assert<Double>.() -> Unit)?>{ override fun toList() = listOf<(Assert<Double>.() -> Unit)?>() }
                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
