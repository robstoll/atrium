package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableToContainInOrderOnlyValuesExpectationsShortcutTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair()
    ) {
    companion object : IterableToContainSpecBase() {
        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyValuesShortcut

        private fun toContainInOrderOnlyValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else expect toContainExactly values(a, *aX, reportOptionsInOrderOnly = report)

        private val toContainNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactly

        fun getToContainNullableShortcutPair() =
            toContainNullableShortcutFun.name to Companion::toContainInOrderOnlyNullableValuesShortcut

        private fun toContainInOrderOnlyNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else expect toContainExactly values(a, *aX, reportOptionsInOrderOnly = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1))
        var nCollection: Expect<Collection<Number?>> = expect(setOf(null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var starSet: Expect<Set<*>> = expect(setOf(1))
        var starCollection: Expect<Collection<*>> = expect(listOf(null))

        list = list toContainExactly 1
        nSet = nSet toContainExactly 1
        nCollection = nCollection toContainExactly null
        subList = subList toContainExactly 1
        starSet = starSet toContainExactly 1
        starCollection = starCollection toContainExactly null

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSetValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var nCollectionValues: Expect<Set<Number?>> = expect(setOf(null, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2))
        var starSetValues: Expect<Set<*>> = expect(setOf(1, 1.2, "asdf"))
        var starCollectionValues: Expect<Collection<*>> = expect(listOf(1, null, "asdf"))

        listValues = listValues toContainExactly values(1, 1.2)
        nSetValues = nSetValues toContainExactly values(1, 1.2)
        nCollectionValues = nCollectionValues toContainExactly values(null, 1.2)
        subListValues = subListValues toContainExactly values(1, 1.2)
        starSetValues = starSetValues toContainExactly values(1, 1.2, "asdf")
        starCollectionValues = starCollectionValues toContainExactly values(1, null, "asdf")

        listValues = listValues toContainExactly values(1, 1.2, reportOptionsInOrderOnly = {
            showOnlyFailingIfMoreExpectedElementsThan(1)
        })
        nSetValues = nSetValues toContainExactly values(1, 1.2, reportOptionsInOrderOnly = { showOnlyFailing() })
        subListValues = subListValues toContainExactly values(1, 1.2, reportOptionsInOrderOnly = { showAlwaysSummary() })
        starSetValues = starSetValues toContainExactly values(1, 1.2, "asdf", reportOptionsInOrderOnly = {})
        starCollectionValues = starCollectionValues toContainExactly values(1, null, "asdf", reportOptionsInOrderOnly = {})
    }
}
