package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class IterableContainsInOrderOnlyGroupedValuesSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedValuesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inOrder $butOnly $groupedEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrder

        private fun containsInOrderOnlyGroupedInAnyOrder(
            plant: Assert<Iterable<Double>>,
            a1: GroupWithoutNullableEntries<Double>,
            a2: GroupWithoutNullableEntries<Double>,
            aX: Array<out GroupWithoutNullableEntries<Double>>
        ): Assert<Iterable<Double>> {
            return plant to contain inGiven order and only grouped entries within group inAny Order(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Double>): GroupWithoutNullableEntries<Double> {
            return when (groups.size) {
                0 -> object : GroupWithoutNullableEntries<Double> { override fun toList() = listOf<Double>() }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
