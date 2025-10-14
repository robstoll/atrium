package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
        functionDescription to Companion::toContainInAnyOrderOnlyValues,
        (functionDescription to Companion::toContainInAnyOrderOnlyNullableValues).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$value/$values"

        private fun toContainInAnyOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inAnyOrder.only.value(a)
                else expect.toContain.inAnyOrder.only.values(a, *aX)
            } else expect.toContain.inAnyOrder.only.values(a, *aX, report = report)

        private fun toContainInAnyOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inAnyOrder.only.value(a)
                else expect.toContain.inAnyOrder.only.values(a, *aX)
            } else expect.toContain.inAnyOrder.only.values(a, *aX, report = report)
    }

    @Suppress("AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nListValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1, 1.2))
        var starValues: Expect<Collection<*>> = expect(listOf(1, 1.2))

        list = list.toContain.inAnyOrder.only.value(1)
        nList = nList.toContain.inAnyOrder.only.value(1)
        subList = subList.toContain.inAnyOrder.only.value(1)
        star = star.toContain.inAnyOrder.only.value(1)

        listValues = listValues.toContain.inAnyOrder.only.values(1, 1.2)
        nListValues = nListValues.toContain.inAnyOrder.only.values(1, 1.2)
        subListValues = subListValues.toContain.inAnyOrder.only.values(1, 2.2)
        starValues = starValues.toContain.inAnyOrder.only.values(1, 1.2, "asdf")

        listValues = listValues.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        nListValues = nListValues.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        subListValues = subListValues.toContain.inAnyOrder.only.values(1, 2.2, report = {})
        starValues = starValues.toContain.inAnyOrder.only.values(1, 1.2, "asdf", report = {})

        nListValues = nListValues.toContain.inAnyOrder.only.values(null, 1.2)
        starValues = starValues.toContain.inAnyOrder.only.values(null, 1.2, "asdf")

        nListValues = nListValues.toContain.inAnyOrder.only.values(null, 1.2, report = {})
        starValues = starValues.toContain.inAnyOrder.only.values(null, 1.2, "asdf", report = {})
    }
}
