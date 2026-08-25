package ch.tutteli.atrium.api.infix.en_GB


import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyEntriesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyEntriesExpectationsTest :
    AbstractIterableToContainInAnyOrderOnlyEntriesExpectationsTest(
        getContainsPair(),
        getContainsNullablePair()
    ) {

    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyEntries

        private fun toContainInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only entry a
                else expect toContain o inAny order but only the entries(a, *aX)
            } else expect toContain o inAny order but only the entries(a, *aX, reportOptionsInAnyOrderOnly = report)


        fun getContainsNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyNullableEntries

        private fun toContainInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only entry a
                else expect toContain o inAny order but only the entries(a, *aX)
            } else expect toContain o inAny order but only the entries(a, *aX, reportOptionsInAnyOrderOnly = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        var nNullable: Expect<Collection<Number?>> = expect(listOf(null))
        var starNullable: Expect<Collection<*>> = expect(listOf(null))

        var listEntries: Expect<List<Number>> = expect(listOf(1, 2))
        var nSetEntries: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subListEntries: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var starEntries: Expect<Collection<*>> = expect(listOf(1, 2))

        var nCollectionEntries: Expect<Collection<Number?>> =
            expect(listOf(null, 1, null))

        var starNullableEntries: Expect<Collection<*>> =
            expect(listOf(null, 1, null))

        list = list toContain o inAny order but only entry { toEqual(1) }
        nSet = nSet toContain o inAny order but only entry { toEqual(1) }
        subList = subList toContain o inAny order but only entry { toEqual(1) }
        star = star toContain o inAny order but only entry { toEqual(1) }

        nNullable = nNullable toContain o inAny order but only entry (null)
        starNullable = starNullable toContain o inAny order but only entry (null)

        listEntries = listEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) }
        )
        nSetEntries = nSetEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) }
        )
        subListEntries = subListEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) }
        )
        starEntries = starEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) }
        )


        listEntries = listEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) },
            reportOptionsInAnyOrderOnly = {}
        )
        nSetEntries = nSetEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) },
            reportOptionsInAnyOrderOnly = {}
        )
        subListEntries = subListEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) },
            reportOptionsInAnyOrderOnly = {}
        )
        starEntries = starEntries toContain o inAny order but only the entries(
            { toEqual(1) }, { toEqual(2) },
            reportOptionsInAnyOrderOnly = {}
        )

        nCollectionEntries =
            nCollectionEntries toContain o inAny order but only the entries(
                null, { toEqual(1) }, null
            )

        starNullableEntries =
            starNullableEntries toContain o inAny order but only the entries(
                null, { toEqual(1) }, null
            )

        nCollectionEntries =
            nCollectionEntries toContain o inAny order but only the entries(
                null, { toEqual(1) }, null,
                reportOptionsInAnyOrderOnly = {}
            )

        starNullableEntries =
            starNullableEntries toContain o inAny order but only the entries(
                null, { toEqual(1) }, null,
                reportOptionsInAnyOrderOnly = {}
            )
    }
}
