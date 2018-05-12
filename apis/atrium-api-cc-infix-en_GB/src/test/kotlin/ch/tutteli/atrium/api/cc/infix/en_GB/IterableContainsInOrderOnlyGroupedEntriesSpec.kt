package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.*
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

class IterableContainsInOrderOnlyGroupedEntriesSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inOrder $butOnly $groupedEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrder

        private fun containsInOrderOnlyGroupedInAnyOrder(
            plant: Assert<Iterable<Double>>,
            a1: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>,
            a2: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>,
            aX: Array<out GroupWithoutNullableEntries<Assert<Double>.() -> Unit>>
        ): Assert<Iterable<Double>> {
            return plant to contain inGiven order and only grouped entries within group inAny Order(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Assert<Double>.() -> Unit>): GroupWithoutNullableEntries<Assert<Double>.() -> Unit> {
            return when(groups.size){
                0 -> object: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>{ override fun toList() = listOf<Assert<Double>.() -> Unit>() }
                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
