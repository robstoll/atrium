package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    getContainsNullablePair(),
    Companion::nullableGroupFactory,
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderValues

        private fun containsInOrderOnlyGroupedInAnyOrderValues(
            plant: Assert<Iterable<Double>>,
            a1: GroupWithoutNullableEntries<Double>,
            a2: GroupWithoutNullableEntries<Double>,
            aX: Array<out GroupWithoutNullableEntries<Double>>
        ): Assert<Iterable<Double>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Double>): GroupWithoutNullableEntries<Double> {
            return when (groups.size) {
                0 -> object : GroupWithoutNullableEntries<Double> { override fun toList() = listOf<Double>() }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder nullable" to Companion::containsInOrderOnlyGroupedInAnyOrderNullableValues

        private fun containsInOrderOnlyGroupedInAnyOrderNullableValues(
            plant: Assert<Iterable<Double?>>,
            a1: GroupWithNullableEntries<Double?>,
            a2: GroupWithNullableEntries<Double?>,
            aX: Array<out GroupWithNullableEntries<Double?>>
        ): Assert<Iterable<Double?>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun nullableGroupFactory(groups: Array<out Double?>): GroupWithNullableEntries<Double?> {
            return when (groups.size) {
                0 -> object : GroupWithNullableEntries<Double?> { override fun toList() = listOf<Double>() }
                1 -> NullableValue(groups[0])
                else -> NullableValues(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
