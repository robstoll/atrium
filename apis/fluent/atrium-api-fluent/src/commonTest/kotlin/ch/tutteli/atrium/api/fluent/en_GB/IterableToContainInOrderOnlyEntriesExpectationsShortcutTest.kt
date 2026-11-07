package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyEntriesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction4
import kotlin.test.Test

class IterableToContainInOrderOnlyEntriesExpectationsShortcutTest :
    AbstractIterableToContainInOrderOnlyEntriesExpectationsTest(
        shortcutFunctionDescription to Companion::toContainExactly,
        (shortcutFunctionDescription to Companion::toContainExactlyNullable).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        private val toContainExactlyFun: KFunction4<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InOrderOnlyReportingOptions.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly
        val shortcutFunctionDescription = toContainExactlyFun.name

        private fun toContainExactly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> = expect.toContainExactly(a, *aX, report = report)

        private fun toContainExactlyNullable(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> = expect.toContainExactly(a, *aX, report = report)
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

        list = list.toContainExactly { toEqual(1) }
        nSet = nSet.toContainExactly { toEqual(1) }
        subList = subList.toContainExactly { toEqual(1) }
        star = star.toContainExactly { toEqual(1) }

        nSetNullable = nSetNullable.toContainExactly(null)
        starNullable = starNullable.toContainExactly(null)

        var listEntries: Expect<List<Number>> = expect(listOf(1, 2))
        var nSetEntries: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subListEntries: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var starEntries: Expect<Collection<*>> = expect(listOf(1, 2))

        var nCollectionEntries: Expect<Collection<Number?>> = expect(listOf(null, 1, null))
        var starNullableEntries: Expect<Collection<*>> = expect(listOf(null, 1, null))

        listEntries = listEntries.toContainExactly({ toEqual(1) }, { toEqual(2) })
        nSetEntries = nSetEntries.toContainExactly({ toEqual(1) }, { toEqual(2) })
        subListEntries = subListEntries.toContainExactly({ toEqual(1) }, { toEqual(2) })
        starEntries = starEntries.toContainExactly({ toEqual(1) }, { toEqual(2) })

        listEntries = listEntries.toContainExactly(
            { toEqual(1) },
            { toEqual(2) },
            report = { showOnlyFailingIfMoreExpectedElementsThan(20) })
        nSetEntries = nSetEntries.toContainExactly(
            { toEqual(1) },
            { toEqual(2) },
            report = { showOnlyFailing() })
        subListEntries = subListEntries.toContainExactly(
            { toEqual(1) },
            { toEqual(2) },
            report = { showAlwaysSummary() })
        starEntries = starEntries.toContainExactly({ toEqual(1) }, { toEqual(2) }, report = { })

        nCollectionEntries = nCollectionEntries.toContainExactly(null, { toEqual(1) }, null)
        starNullableEntries = starNullableEntries.toContainExactly(null, { toEqual(1) }, null)

        nCollectionEntries = nCollectionEntries.toContainExactly(null, { toEqual(1) }, null, report = { })
        starNullableEntries = starNullableEntries.toContainExactly(null, { toEqual(1) }, null, report = { })
    }
}
