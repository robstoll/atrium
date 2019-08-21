package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group

class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        Companion::groupFactory,
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderEntries

        private fun containsInOrderOnlyGroupedInAnyOrderEntries(
            plant: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>
        ): Expect<Iterable<Double?>> {
            return plant.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = listOf<Expect<Double>.() -> Unit>()
                }
                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }
}
