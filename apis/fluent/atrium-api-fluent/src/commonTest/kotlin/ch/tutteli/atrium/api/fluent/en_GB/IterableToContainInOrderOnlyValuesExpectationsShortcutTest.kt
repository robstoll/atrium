package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction4
import kotlin.test.Test

class IterableToContainInOrderOnlyValuesExpectationsShortcutTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        shortcutFunctionDescription to Companion::toContainExactlyValues,
        (shortcutFunctionDescription to Companion::toContainExactlyNullableValues).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        private val toContainExactlyFun: KFunction4<Expect<Iterable<Double>>, Double, Array<out Double>, InOrderOnlyReportingOptions.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly
        val shortcutFunctionDescription = toContainExactlyFun.name

        private fun toContainExactlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> = expect.toContainExactly(a, *aX, report = report)

        private fun toContainExactlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
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

        list = list.toContainExactly(1)
        nSet = nSet.toContainExactly(1)
        subList = subList.toContainExactly(1)
        star = star.toContainExactly(1)

        nSetNullable = nSetNullable.toContainExactly(null)
        starNullable = starNullable.toContainExactly(null)

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSetValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2))
        var starValues: Expect<Collection<*>> = expect(listOf(1, 1.2))

        listValues = listValues.toContainExactly(1, 1.2)
        nSetValues = nSetValues.toContainExactly(1, 1.2)
        subListValues = subListValues.toContainExactly(1, 1.2)
        starValues = starValues.toContainExactly(1, 1.2)

        listValues = listValues.toContainExactly(
            1,
            1.2,
            report = { showOnlyFailingIfMoreExpectedElementsThan(20) })
        nSetValues = nSetValues.toContainExactly(
            1,
            1.2,
            report = { showOnlyFailing() })
        subListValues = subListValues.toContainExactly(
            1,
            1.2,
            report = { showAlwaysSummary() })
        starValues = starValues.toContainExactly(1, 1.2, report = {})

        var nCollectionNullableValues: Expect<Collection<Number?>> = expect(listOf(null, 1, null))
        var starNullableValues: Expect<Collection<*>> = expect(listOf(null, 1, null))

        nCollectionNullableValues = nCollectionNullableValues.toContainExactly(null, 1, null)
        starNullableValues = starNullableValues.toContainExactly(null, 1, null)

        nCollectionNullableValues = nCollectionNullableValues.toContainExactly(null, 1, null, report = { })
        starNullableValues = starNullableValues.toContainExactly(null, 1, null, report = { })
    }
}
