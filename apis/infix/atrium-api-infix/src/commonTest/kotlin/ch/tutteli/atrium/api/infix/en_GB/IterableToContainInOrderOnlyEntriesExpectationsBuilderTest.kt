package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyEntriesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInOrderOnlyEntriesExpectationsBuilderTest :
    AbstractIterableToContainInOrderOnlyEntriesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair()
    ) {
    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::toContainInOrderOnly

        private fun toContainInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else expect toContain o inGiven order and only the entries(a, *aX, reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::toContainInOrderOnlyNullableEntriesPair

        private fun toContainInOrderOnlyNullableEntriesPair(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else expect toContain o inGiven order and only the entries(a, *aX, reportOptionsInOrderOnly = report)
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

        list = list toContain o inGiven order and only entry { toEqual(1) }
        nSet = nSet toContain o inGiven order and only entry { toEqual(1) }
        subList = subList toContain o inGiven order and only entry { toEqual(1) }
        star = star toContain o inGiven order and only entry { toEqual(1) }

        nSetNullable = nSetNullable toContain o inGiven order and only entry (null)
        starNullable = starNullable toContain o inGiven order and only entry (null)

        var listEntries: Expect<List<Number>> = expect(listOf(1, 2))
        var nSetEntries: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subListEntries: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var starEntries: Expect<Collection<*>> = expect(listOf(1, 2))

        var nCollectionEntries: Expect<Collection<Number?>> = expect(listOf(null, 1, null))
        var starNullableEntries: Expect<Collection<*>> = expect(listOf(null, 1, null))

        listEntries = listEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) })
        nSetEntries = nSetEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) })
        subListEntries = subListEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) })
        starEntries = starEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) })

        nCollectionEntries =
            nCollectionEntries toContain o inGiven order and only the entries(null, { toEqual(1) }, null)
        starNullableEntries =
            starNullableEntries toContain o inGiven order and only the entries(null, { toEqual(1) }, null)

        listEntries =
            listEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) }, reportOptionsInOrderOnly = { })
        nSetEntries =
            nSetEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) }, reportOptionsInOrderOnly = { })
        subListEntries =
            subListEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) }, reportOptionsInOrderOnly = { })
        starEntries =
            starEntries toContain o inGiven order and only the entries({ toEqual(1) }, { toEqual(2) }, reportOptionsInOrderOnly = { })

        nCollectionEntries =
            nCollectionEntries toContain o inGiven order and only the entries(null, { toEqual(1) }, null, reportOptionsInOrderOnly = { })
        starNullableEntries =
            starNullableEntries toContain o inGiven order and only the entries(null, { toEqual(1) }, null, reportOptionsInOrderOnly = { })
    }
}
