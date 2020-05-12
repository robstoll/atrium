// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.migration.asExpectGroup
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inOrder $butOnly $groupedEntries $withinGroup $withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderEntries

        private fun containsInOrderOnlyGroupedInAnyOrderEntries(
            plant: Assert<Iterable<Double?>>,
            a1: Group<(Assert<Double>.() -> Unit)?>,
            a2: Group<(Assert<Double>.() -> Unit)?>,
            aX: Array<out Group<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            val order1 = Order(a1, a2, *aX)
            return (((plant.asExpect().contains(o) inGiven order).and(ch.tutteli.atrium.api.infix.en_GB.only) grouped ch.tutteli.atrium.api.infix.en_GB.entries).within(
                ch.tutteli.atrium.api.infix.en_GB.group
            ) inAny order(
                asExpectGroup(order1.firstGroup),
                asExpectGroup(order1.secondGroup),
                *order1.otherExpectedGroups.map {
                    asExpectGroup(
                        it
                    )
                }
                    .toTypedArray()
            )).asAssert()
        }

        private fun groupFactory(groups: Array<out (Assert<Double>.() -> Unit)?>): Group<(Assert<Double>.() -> Unit)?> {
            return when(groups.size){
                0 -> object: Group<(Assert<Double>.() -> Unit)?>{ override fun toList() = listOf<Assert<Double>.() -> Unit>() }
                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
