package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group

class IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec(
        getContainsPair(),
        Companion::groupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderEntries

        private fun toContainInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>
        ): Expect<Iterable<Double?>> =
            expect toContain o inGiven order and only grouped entries within group inAny order(a1, a2, *aX)

        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = listOf<Expect<Double>.() -> Unit>()
                }
                1 -> entry(groups[0])
                else -> entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }
}
