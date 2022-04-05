package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInOrderOnlyGroupedValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedValuesExpectationsSpec(
        getContainsPair(),
        Companion::groupFactory,
        getContainsNullablePair(),
        Companion::nullableGroupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderValues

        private fun toContainInOrderOnlyGroupedInAnyOrderValues(
            expect: Expect<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report == emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(a1, a2, *aX)
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report
                )
            } else if (report == emptyInOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, reportInGroup = reportInGroup
                )
            } else {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report, reportInGroup = reportInGroup
                )
            }

        private fun groupFactory(groups: Array<out Double>): Group<Double> =
            when (groups.size) {
                0 -> object : Group<Double> {
                    override fun toList() = listOf<Double>()
                }
                1 -> value(groups[0])
                else -> values(groups[0], *groups.drop(1).toTypedArray())
            }

        fun getContainsNullablePair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderNullableValues

        private fun toContainInOrderOnlyGroupedInAnyOrderNullableValues(
            expect: Expect<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report == emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(a1, a2, *aX)
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report
                )
            } else if (report == emptyInOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, reportInGroup = reportInGroup
                )
            } else {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report, reportInGroup = reportInGroup
                )
            }

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> =
            when (groups.size) {
                0 -> object : Group<Double?> {
                    override fun toList() = listOf<Double>()
                }
                1 -> value(groups[0])
                else -> values(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            reportInGroup = {}
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {},
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {},
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {},
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {},
            reportInGroup = {}
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number?>(1.2, null)
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2, 1.2)
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            report = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            report = {}
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            reportInGroup = {}
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            report = {},
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            report = {},
            reportInGroup = {}
        )
    }
}
