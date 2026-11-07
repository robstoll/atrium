package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToContainInOrderOnlyGroupedValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedValuesExpectationsSpec(
        functionDescription to Companion::toContainInOrderOnlyGroupedInAnyOrderValues,
        Companion::groupFactory,
        (functionDescription to Companion::toContainInOrderOnlyGroupedInAnyOrderNullableValues).withNullableSuffix(),
        Companion::nullableGroupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$grouped.$within.$withinInAnyOrder"

        private fun toContainInOrderOnlyGroupedInAnyOrderValues(
            expect: Expect<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
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


        private fun groupFactory(groups: Array<out Double>): Group<Double> =
            when (groups.size) {
                0 -> object : Group<Double> {
                    override fun toList() = emptyList<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }

        private fun toContainInOrderOnlyGroupedInAnyOrderNullableValues(
            expect: Expect<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>,
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

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> =
            when (groups.size) {
                0 -> object : Group<Double?> {
                    override fun toList() = emptyList<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2))
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1.2, 2))
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1.2), Values(1, 2))
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1f), Values(1L, 2, 'a', "hello"))

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), report = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), report = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), report = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), report = {})

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), reportInGroup = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), reportInGroup = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), reportInGroup = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2), reportInGroup = {})

        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1, 2),
            report = {},
            reportInGroup = {})
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1, 2),
            report = {},
            reportInGroup = {})
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1, 2),
            report = {},
            reportInGroup = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1, 2),
            report = {},
            reportInGroup = {})

        val value1 = Value<Int?>(null)
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(value1, Values(1, null))
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(null, 2))

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(1, null), report = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(null, 2), report = {})

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(1, null), reportInGroup = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(null, 2), reportInGroup = {})

        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(null),
            Values(1, null),
            report = {},
            reportInGroup = {})
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder(
            Value(null),
            Values(null, 2),
            report = {},
            reportInGroup = {})
    }
}
