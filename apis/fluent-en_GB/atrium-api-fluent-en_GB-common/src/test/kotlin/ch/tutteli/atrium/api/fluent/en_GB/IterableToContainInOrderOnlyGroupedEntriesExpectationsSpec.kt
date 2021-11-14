package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec(
        functionDescription to Companion::toContainInOrderOnlyGroupedInAnyOrderEntries,
        Companion::groupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$grouped.$within.$withinInAnyOrder"

        private fun toContainInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>
        ): Expect<Iterable<Double?>> = expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)

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

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Entry<Number> {}, Entries<Number>({}, {}))

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Entry<Number> {}, Entries<Number>({}, {}))

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries({}, null))
        //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Entry<Number>(null), Entries<Number>(null, {}))

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries({}, null), report = {})
        //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Entry<Number>(null), Entries<Number>(null, {}))
    }
}
