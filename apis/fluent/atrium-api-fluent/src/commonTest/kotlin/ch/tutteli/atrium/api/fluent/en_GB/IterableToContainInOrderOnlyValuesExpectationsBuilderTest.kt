package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInOrderOnlyValuesExpectationsBuilderTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        functionDescription to Companion::toContainInOrderOnlyValues,
        (functionDescription to Companion::toContainInOrderOnlyNullableValues).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$value/$values"

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
                else expect.toContain.inOrder.only.values(a, *aX)
            } else expect.toContain.inOrder.only.values(a, *aX, report = report)

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
                else expect.toContain.inOrder.only.values(a, *aX)
            } else expect.toContain.inOrder.only.values(a, *aX, report = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        var nSetNullable: Expect<Set<Number?>> = expect(setOf(null, 1.2))
        var starNullable: Expect<Collection<*>> = expect(listOf(null, 1.2, "asdf"))

        list = list.toContain.inOrder.only.value(1)
        nSet = nSet.toContain.inOrder.only.value(1)
        subList = subList.toContain.inOrder.only.value(1)
        star = star.toContain.inOrder.only.value(1)

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSetValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2))
        var starValues: Expect<Collection<*>> = expect(listOf(1, 1.2, "asdf"))

        listValues = listValues.toContain.inOrder.only.values(1, 1.2)
        nSetValues = nSetValues.toContain.inOrder.only.values(1, 1.2)
        subListValues = subListValues.toContain.inOrder.only.values(1, 1.2)
        starValues = starValues.toContain.inOrder.only.values(1, 1.2, "asdf")

        listValues = listValues.toContain.inOrder.only.values(1, 1.2, report = {})
        nSetValues = nSetValues.toContain.inOrder.only.values(1, 1.2, report = {})
        subListValues = subListValues.toContain.inOrder.only.values(1, 1.2, report = {})
        starValues = starValues.toContain.inOrder.only.values(1, 1.2, "asdf", report = {})

        nSetNullable = nSetNullable.toContain.inOrder.only.values(null, 1.2)
        starNullable = starNullable.toContain.inOrder.only.values(null, 1.2, "asdf")

        nSetNullable = nSetNullable.toContain.inOrder.only.values(null, 1.2, report = {})
        starNullable = starNullable.toContain.inOrder.only.values(null, 1.2, "asdf", report = {})

        list = list.toContainExactly(1)
        nSet = nSet.toContainExactly(1)
        subList = subList.toContainExactly(1)
        star = star.toContainExactly(1)

        listValues = listValues.toContainExactly(1, 1.2)
        nSetValues = nSetValues.toContainExactly(1, 1.2)
        subListValues = subListValues.toContainExactly(1, 1.2)

        var starNullableValues: Expect<Collection<*>> = expect(listOf(1, "asdf", null))
        starNullableValues = starNullableValues.toContainExactly(1, "asdf", null)

        listValues = listValues.toContainExactly(1, 1.2, report = { showOnlyFailingIfMoreExpectedElementsThan(1) })
        nSetValues = nSetValues.toContainExactly(1, 1.2, report = { showOnlyFailing() })
        subListValues = subListValues.toContainExactly(1, 1.2, report = { showAlwaysSummary() })
        starNullableValues = starNullableValues.toContainExactly(1, "asdf", null, report = {})
    }
}
