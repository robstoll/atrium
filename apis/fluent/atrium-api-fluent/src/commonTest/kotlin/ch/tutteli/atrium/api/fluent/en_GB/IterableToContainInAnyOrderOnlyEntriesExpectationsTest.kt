package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyEntriesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyEntriesExpectationsTest :
    AbstractIterableToContainInAnyOrderOnlyEntriesExpectationsTest(
        functionDescription to Companion::toContainInAnyOrderOnlyEntries,
        (functionDescription to Companion::toContainInAnyOrderOnlyNullableEntries).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$entry/$entries"

        private fun toContainInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
                else expect.toContain.inAnyOrder.only.entries(a, *aX)
            } else expect.toContain.inAnyOrder.only.entries(a, *aX, report = report)


        private fun toContainInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
                else expect.toContain.inAnyOrder.only.entries(a, *aX)
            } else expect.toContain.inAnyOrder.only.entries(a, *aX, report = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {

        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        var nSetNullable: Expect<Set<Number?>> = expect(setOf(null))
        var starNullable: Expect<Collection<*>> = expect(listOf(null))

        list = list.toContain.inAnyOrder.only.entry { toEqual(1) }
        nSet = nSet.toContain.inAnyOrder.only.entry { toEqual(1) }
        subList = subList.toContain.inAnyOrder.only.entry { toEqual(1) }
        star = star.toContain.inAnyOrder.only.entry { toEqual(1) }

        nSetNullable = nSetNullable.toContain.inAnyOrder.only.entry(null)
        starNullable = starNullable.toContain.inAnyOrder.only.entry(null)

        var listEntries: Expect<List<Number>> = expect(listOf(1, 2))
        var nSetEntries: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subListEntries: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var starEntries: Expect<Collection<*>> = expect(listOf(1, 2))

        var nCollectionEntries: Expect<Collection<Number?>> =
            expect(listOf(null, 1, null))

        var starNullableEntries: Expect<Collection<*>> =
            expect(listOf(null, 1, null))

        listEntries = listEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }
        )
        nSetEntries = nSetEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }
        )
        subListEntries = subListEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }
        )
        starEntries = starEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }
        )

        listEntries = listEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }, report = {}
        )
        nSetEntries = nSetEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }, report = {}
        )
        subListEntries = subListEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }, report = {}
        )
        starEntries = starEntries.toContain.inAnyOrder.only.entries(
            { toEqual(1) }, { toEqual(2) }, report = {}
        )


        nCollectionEntries =
            nCollectionEntries.toContain.inAnyOrder.only.entries(null, { toEqual(1) }, null)

        starNullableEntries =
            starNullableEntries.toContain.inAnyOrder.only.entries(null, { toEqual(1) }, null)

        nCollectionEntries =
            nCollectionEntries.toContain.inAnyOrder.only.entries(null, { toEqual(1) }, null, report = {})

        starNullableEntries =
            starNullableEntries.toContain.inAnyOrder.only.entries(null, { toEqual(1) }, null, report = {})
    }
}
