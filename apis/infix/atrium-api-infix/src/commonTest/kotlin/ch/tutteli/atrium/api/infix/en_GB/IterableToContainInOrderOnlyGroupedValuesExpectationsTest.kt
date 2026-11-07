package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyGroupedValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInOrderOnlyGroupedValuesExpectationsTest :
    AbstractIterableToContainInOrderOnlyGroupedValuesExpectationsTest(
        getContainsPair(),
        Companion::groupFactory,
        getContainsNullablePair(),
        Companion::nullableGroupFactory
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
                    override fun toList() = emptyList<Double>()
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
                    override fun toList() = emptyList<Double>()
                }
                1 -> value(groups[0])
                else -> values(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1, 2.2))
        var nList: Expect<Set<Number?>> = expect(setOf(1, 1.2, 2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1, 2.2))
        var star: Expect<Collection<*>> = expect(listOf(1, 1, 2))

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2)
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1.2, 2)
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2)
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2)
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            report = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1.2, 2),
            report = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            report = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1.2, 2),
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            reportInGroup = {}
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            report = {},
            reportInGroup = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1.2, 2),
            report = {},
            reportInGroup = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2.2),
            report = {},
            reportInGroup = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {},
            reportInGroup = {}
        )

        var nListNullable: Expect<Set<Number?>> = expect(setOf(null, 1, 2))
        var starNullable: Expect<Collection<*>> = expect(listOf(null, null, 2))

        nListNullable = nListNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1),
            values(2)
        )
        starNullable = starNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2)
        )

        nListNullable = nListNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1),
            values(2),
            report = {}
        )
        starNullable = starNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            report = {}
        )

        nListNullable = nListNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1),
            values(2),
            reportInGroup = {}
        )
        starNullable = starNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            reportInGroup = {}
        )

        nListNullable = nListNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1),
            values(2),
            report = {},
            reportInGroup = {}
        )
        starNullable = starNullable toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            report = {},
            reportInGroup = {}
        )
    }
}
