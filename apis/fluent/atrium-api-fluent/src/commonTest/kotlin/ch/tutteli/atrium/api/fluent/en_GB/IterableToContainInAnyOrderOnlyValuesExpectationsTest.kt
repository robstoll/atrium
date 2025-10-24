package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyValuesExpectationsTest :
    AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1))
        var nCollection: Expect<Collection<Number?>> = expect(setOf(null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var starSet: Expect<Set<*>> = expect(setOf(1))
        var starCollection: Expect<Collection<*>> = expect(listOf(null))

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSetValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var nCollectionValues: Expect<Set<Number?>> = expect(setOf(null, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2))
        var starSetValues: Expect<Set<*>> = expect(setOf(1, 1.2, "asdf"))
        var starCollectionValues: Expect<Collection<*>> = expect(listOf(1, null, "asdf"))

        list = list.toContain.inAnyOrder.only.value(1)
        nSet = nSet.toContain.inAnyOrder.only.value(1)
        nCollection = nCollection.toContain.inAnyOrder.only.value(null)
        subList = subList.toContain.inAnyOrder.only.value(1)
        starSet = starSet.toContain.inAnyOrder.only.value(1)
        starCollection = starCollection.toContain.inAnyOrder.only.value(null)

        listValues = listValues.toContain.inAnyOrder.only.values(1, 1.2)
        nSetValues = nSetValues.toContain.inAnyOrder.only.values(1, 1.2)
        nCollectionValues = nCollectionValues.toContain.inAnyOrder.only.values(null, 1.2)
        subListValues = subListValues.toContain.inAnyOrder.only.values(1, 1.2)
        starSetValues = starSetValues.toContain.inAnyOrder.only.values(1, 1.2, "asdf")
        starCollectionValues = starCollectionValues.toContain.inAnyOrder.only.values(1, null, "asdf")

        listValues = listValues.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        nSetValues = nSetValues.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        subListValues = subListValues.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        nCollectionValues = nCollectionValues.toContain.inAnyOrder.only.values(null, 1.2, report = {})
        starSetValues = starSetValues.toContain.inAnyOrder.only.values(1, 1.2, "asdf", report = {})
        starCollectionValues = starCollectionValues.toContain.inAnyOrder.only.values(1, null, "asdf", report = {})
    }
}
