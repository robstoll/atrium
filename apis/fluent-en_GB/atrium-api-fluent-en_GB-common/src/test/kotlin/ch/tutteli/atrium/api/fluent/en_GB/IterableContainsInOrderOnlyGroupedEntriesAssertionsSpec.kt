package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.specs.notImplemented

class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
         functionDescription to Companion::containsInOrderOnlyGroupedInAnyOrderEntries,
        Companion::groupFactory,
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        val functionDescription =  "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder"

        private fun containsInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)

        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = listOf<Expect<Double>.() -> Unit>()
                }
                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inOrder.only.grouped.within.inAnyOrder(Entry{}, Entries({}, {}))
        nList = nList.contains.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        subList = subList.contains.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        // TODO 1.0.0: not supported, but OK for this rare case, maybe supported with Kotlin 1.4
        //star = star.contains.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))

        nList = nList.contains.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries({}, null))
        // TODO 1.0.0: not supported, but OK for this rare case, maybe supported with Kotlin 1.4
        //star = star.contains.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries(null, {}))
    }
}
