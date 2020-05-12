// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
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
            = "$toContain $inOrder $butOnly $groupedEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrder

        private fun containsInOrderOnlyGroupedInAnyOrder(
            plant: Assert<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>
        ): Assert<Iterable<Double>> {
            val order = Order(a1, a2, *aX)
            return (((plant.asExpect().contains(o) inGiven ch.tutteli.atrium.api.infix.en_GB.order).and(ch.tutteli.atrium.api.infix.en_GB.only) grouped ch.tutteli.atrium.api.infix.en_GB.entries).within(
                ch.tutteli.atrium.api.infix.en_GB.group
            ) inAny order(
                order.firstGroup,
                order.secondGroup,
                *order.otherExpectedGroups
            )).asAssert()
        }

        private fun groupFactory(groups: Array<out Double>): Group<Double> {
            return when (groups.size) {
                0 -> object : Group<Double> { override fun toList() = listOf<Double>() }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
        }

        fun getContainsNullablePair() =
            "$toContain $inOrder $butOnly $groupedEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderNullableValues

        private fun containsInOrderOnlyGroupedInAnyOrderNullableValues(
            plant: Assert<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>
        ): Assert<Iterable<Double?>> {
            val order1 = Order(a1, a2, *aX)
            return (((plant.asExpect().contains(o) inGiven order).and(ch.tutteli.atrium.api.infix.en_GB.only) grouped ch.tutteli.atrium.api.infix.en_GB.entries).within(
                ch.tutteli.atrium.api.infix.en_GB.group
            ) inAny order(
                order1.firstGroup,
                order1.secondGroup,
                *order1.otherExpectedGroups
            )).asAssert()
        }

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> {
            return when (groups.size) {
                0 -> object : Group<Double?> { override fun toList() = listOf<Double>() }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
