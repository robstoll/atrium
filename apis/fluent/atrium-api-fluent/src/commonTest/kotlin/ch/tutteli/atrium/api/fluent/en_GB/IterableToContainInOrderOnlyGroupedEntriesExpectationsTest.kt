package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyGroupedEntriesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInOrderOnlyGroupedEntriesExpectationsTest :
    AbstractIterableToContainInOrderOnlyGroupedEntriesExpectationsTest(
        functionDescription to Companion::toContainInOrderOnlyGroupedInAnyOrderEntries,
        (functionDescription to Companion::toContainInOrderOnlyGroupedInAnyOrderEntriesNullable).withNullableSuffix(),
        Companion::groupFactory,
        Companion::nullableGroupFactory
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$grouped.$within.$withinInAnyOrder"

        private fun toContainInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX, report = report)
            } else if (report == emptyInOrderOnlyReportOptions) {
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

        private fun toContainInOrderOnlyGroupedInAnyOrderEntriesNullable(
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
            } else if (report == emptyInOrderOnlyReportOptions) {
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

        private fun groupFactory(
            groups: Array<out Expect<Double>.() -> Unit>
        ): Group<(Expect<Double>.() -> Unit)?> =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = emptyList<(Expect<Double>.() -> Unit)?>()
                }

                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }

        private fun nullableGroupFactory(
            groups: Array<out (Expect<Double>.() -> Unit)?>
        ): Group<(Expect<Double>.() -> Unit)?> =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = emptyList<(Expect<Double>.() -> Unit)?>()
                }

                1 -> Entry(groups[0])
                else -> Entries(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf<Number>(1.0, 2.0, 3.0))
        var nList: Expect<Set<Number?>> = expect(linkedSetOf<Number?>(1.0, 2.0, 3.0))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1.0, 2.0, 3.0))
        var star: Expect<Collection<*>> = expect<Collection<*>>(listOf<Any?>(1.0, 2.0, 3.0))

        // Default reporting options
        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) })
        )
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) })
        )
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) })
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) })
        )

        // InOrder reporting options
        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), report = {}
        )
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), report = {}
        )
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), report = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), report = {}
        )

        // InAnyOrder reporting options
        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), reportInGroup = {}
        )
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), reportInGroup = {}
        )
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry { toEqual(1.0) }, Entries({ toEqual(2.0) }, { toEqual(3.0) }), reportInGroup = {}
        )

        // Both reporting options
        list = list.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) },
            Entries({ toEqual(2.0) }, { toEqual(3.0) }),
            report = {},
            reportInGroup = {}
        )
        nList = nList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) },
            Entries({ toEqual(2.0) }, { toEqual(3.0) }),
            report = {},
            reportInGroup = {}
        )
        subList = subList.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toEqual(1.0) },
            Entries({ toEqual(2.0) }, { toEqual(3.0) }),
            report = {},
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        star = star.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<*>>(
            Entry { toEqual(1.0) },
            Entries({ toEqual(2.0) }, { toEqual(3.0) }),
            report = {},
            reportInGroup = {}
        )

        // A Set can contain only one null. Use distinct non-null values in the second group.
        var nListWithNull: Expect<Set<Number?>> = expect(linkedSetOf<Number?>(null, 1.0, 2.0))
        // Use a List behind the star-projected type so this case can contain two null values.
        var starNullable: Expect<Collection<*>> = expect<Collection<*>>(listOf<Any?>(null, null, 1.0))

        // Nullable overloads with default reporting options
        nListWithNull = nListWithNull.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null), Entries({ toEqual(1.0) }, { toEqual(2.0) })
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        starNullable = starNullable.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry<Any>(null), Entries<Any>(null, { toEqual(1.0) })
        )

        // Nullable overloads with InOrder reporting options
        nListWithNull = nListWithNull.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null), Entries({ toEqual(1.0) }, { toEqual(2.0) }), report = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        starNullable = starNullable.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry<Any>(null), Entries<Any>(null, { toEqual(1.0) }), report = {}
        )

        // Nullable overloads with InAnyOrder reporting options
        nListWithNull = nListWithNull.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null), Entries({ toEqual(1.0) }, { toEqual(2.0) }), reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        starNullable = starNullable.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry<Any>(null), Entries<Any>(null, { toEqual(1.0) }), reportInGroup = {}
        )

        // Nullable overloads with both reporting options
        nListWithNull = nListWithNull.toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry(null),
            Entries({ toEqual(1.0) }, { toEqual(2.0) }),
            report = {},
            reportInGroup = {}
        )
        // TODO type parameter should not be necessary: https://youtrack.jetbrains.com/issue/KT-60976/overload-ambiguity-mismatch-in-case-of-covariance-and-only-in-conjunction-with-Any
        starNullable = starNullable.toContain.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry<Any>(null),
            Entries<Any>(null, { toEqual(1.0) }),
            report = {},
            reportInGroup = {}
        )
    }
}
