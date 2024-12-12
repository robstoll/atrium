package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
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
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX, report = report)
            } else if (report == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX, reportInGroup = reportInGroup)
            } else {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(
                    a1,
                    a2,
                    *aX,
                    report = report,
                    reportInGroup = reportInGroup
                )
            }


        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = emptyList<Expect<Double>.() -> Unit>()
                }

                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}))
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(Entry {}, Entry {})

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), report = {})
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(Entry {},
            Entries({}, {}),
            report = {})

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), reportInGroup = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry {}, Entries({}, {}), reportInGroup = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry {}, Entries({}, {}), reportInGroup = {})
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(Entry {},
            Entries({}, {}),
            reportInGroup = {})

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<*>>(
            Entry {},
            Entries({}, {}),
            report = {},
            reportInGroup = {}
        )

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries({}, null))
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Entry<Number>(null), Entries<Number>(null, {}))

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Entry(null), Entries({}, null), report = {})
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry<Number>(null),
            Entries<Number>(null, {}),
            report = {})

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null),
            Entries({}, null),
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry(null),
            Entries(null, {}),
            reportInGroup = {})

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null),
            Entries({}, null),
            report = {},
            reportInGroup = {})

        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry(null),
            Entries(null, {}),
            report = {},
            reportInGroup = {})
    }
}
