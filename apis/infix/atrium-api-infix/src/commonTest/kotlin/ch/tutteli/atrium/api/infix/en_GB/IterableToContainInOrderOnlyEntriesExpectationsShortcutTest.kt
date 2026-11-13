package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyEntriesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableToContainInOrderOnlyEntriesExpectationsShortcutTest :
    AbstractIterableToContainInOrderOnlyEntriesExpectationsTest(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair()
    ) {
    companion object : IterableToContainSpecBase() {
        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyEntriesShortcut

        private fun toContainInOrderOnlyEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly { a() }
                else expect toContainExactly entries(a, *aX)
            } else expect toContainExactly entries(a, *aX, reportOptionsInOrderOnly = report)

        private val toContainNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactly

        fun getToContainNullableShortcutPair() =
            toContainNullableShortcutFun.name to Companion::toContainInOrderOnlyNullableEntriesShortcut

        private fun toContainInOrderOnlyNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) {
                    //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
                    if (a == null) expect toContainExactly a as Double?
                    else expect toContainExactly { a() }
                } else {
                    expect toContainExactly entries(a, *aX)
                }
            } else expect toContainExactly entries(a, *aX, reportOptionsInOrderOnly = report)
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

        list = list toContainExactly { toEqual(1) }
        nSet = nSet toContainExactly { toEqual(1) }
        subList = subList toContainExactly { toEqual(1) }
        star = star toContainExactly { toEqual(1) }

        nSetNullable = nSetNullable toContainExactly null
        starNullable = starNullable toContainExactly null

        var listEntries: Expect<List<Number>> = expect(listOf(1, 2))
        var nSetEntries: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subListEntries: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var starEntries: Expect<Collection<*>> = expect(listOf(1, 2))

        var nCollectionEntries: Expect<Collection<Number?>> = expect(listOf(null, 1, null))
        var starNullableEntries: Expect<Collection<*>> = expect(listOf(null, 1, null))

        listEntries = listEntries toContainExactly entries({ toEqual(1) }, { toEqual(2) })
        nSetEntries = nSetEntries toContainExactly entries({ toEqual(1) }, { toEqual(2) })
        subListEntries = subListEntries toContainExactly entries({ toEqual(1) }, { toEqual(2) })
        starEntries = starEntries toContainExactly entries({ toEqual(1) }, { toEqual(2) })

        listEntries = listEntries toContainExactly entries(
            { toEqual(1) },
            { toEqual(2) },
            reportOptionsInOrderOnly = { showOnlyFailingIfMoreExpectedElementsThan(20) }
        )
        nSetEntries = nSetEntries toContainExactly entries(
            { toEqual(1) },
            { toEqual(2) },
            reportOptionsInOrderOnly = { showOnlyFailing() }
        )
        subListEntries = subListEntries toContainExactly entries(
            { toEqual(1) },
            { toEqual(2) },
            reportOptionsInOrderOnly = { showAlwaysSummary() }
        )
        starEntries = starEntries toContainExactly entries(
            { toEqual(1) },
            { toEqual(2) },
            reportOptionsInOrderOnly = { }
        )

        nCollectionEntries = nCollectionEntries toContainExactly entries(null, { toEqual(1) }, null)
        starNullableEntries = starNullableEntries toContainExactly entries(null, { toEqual(1) }, null)

        nCollectionEntries =
            nCollectionEntries toContainExactly entries(null, { toEqual(1) }, null, reportOptionsInOrderOnly = {})
        starNullableEntries =
            starNullableEntries toContainExactly entries(null, { toEqual(1) }, null, reportOptionsInOrderOnly = {})
    }
}
