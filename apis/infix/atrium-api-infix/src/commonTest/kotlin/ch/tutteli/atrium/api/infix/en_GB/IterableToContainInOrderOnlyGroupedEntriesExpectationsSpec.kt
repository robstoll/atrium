package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec(
        getContainsPair(),
        Companion::groupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderEntries

        private fun toContainInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX
                )
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report
                )
            } else if (report == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, reportInGroup = reportInGroup
                )
            } else {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report, reportInGroup = reportInGroup
                )
            }


        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = emptyList<Expect<Double>.() -> Unit>()
                }

                1 -> entry(groups[0])
                else -> entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }


    @Suppress("unused", "UNUSED_VARIABLE", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {})
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {})
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {})
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry {},
            entries({}, {})
        ))

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry {},
            entries({}, {}),
            report = {}
        ))

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry {},
            entries({}, {}),
            reportInGroup = {}
        ))

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}, {}),
            report = {},
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry {},
            entries({}, {}),
            report = {},
            reportInGroup = {}
        ))

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries({}, null)
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry(null),
            entries(null, {})
        ))

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries({}, null),
            report = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry(null),
            entries(null, {}),
            report = {}
        ))

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries({}, null),
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry(null),
            entries(null, {}),
            reportInGroup = {}
        ))

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries({}, null),
            report = {},
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = (star toContain o inGiven order and only grouped entries within group).inAny<Any, Collection<*>>(order(
            entry(null),
            entries(null, {}),
            report = {},
            reportInGroup = {}
        ))
    }
}
