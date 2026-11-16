package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInOrderOnlyValuesExpectationsBuilderTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair()
    ) {
    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::toContainInOrderOnlyValues

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else expect toContain o inGiven order and only the values(a, *aX, reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else expect toContain o inGiven order and only the values(a, *aX, reportOptionsInOrderOnly = report)
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

        list = list toContain o inGiven order and only value 1
        nSet = nSet toContain o inGiven order and only value 1
        nCollection = nCollection toContain o inGiven order and only value null
        subList = subList toContain o inGiven order and only value 1
        starSet = starSet toContain o inGiven order and only value 1
        starCollection = starCollection toContain o inGiven order and only value null

        listValues = listValues toContain o inGiven order and only the values(1, 1.2)
        nSetValues = nSetValues toContain o inGiven order and only the values(1, 1.2)
        nCollectionValues = nCollectionValues toContain o inGiven order and only the values(null, 1.2)
        subListValues = subListValues toContain o inGiven order and only the values(1, 1.2)
        starSetValues = starSetValues toContain o inGiven order and only the values(1, 1.2, "asdf")
        starCollectionValues = starCollectionValues toContain o inGiven order and only the values(1, null, "asdf")

        listValues = listValues toContain o inGiven order and only the values(1, 1.2, reportOptionsInOrderOnly = {})
        nSetValues = nSetValues toContain o inGiven order and only the values(1, 1.2, reportOptionsInOrderOnly = {})
        subListValues =
            subListValues toContain o inGiven order and only the values(1, 1.2, reportOptionsInOrderOnly = {})
        nCollectionValues =
            nCollectionValues toContain o inGiven order and only the values(null, 1.2, reportOptionsInOrderOnly = {})
        starSetValues =
            starSetValues toContain o inGiven order and only the values(1, 1.2, "asdf", reportOptionsInOrderOnly = {})
        starCollectionValues =
            starCollectionValues toContain o inGiven order and only the values(
                1,
                null,
                "asdf",
                reportOptionsInOrderOnly = {}
            )
    }
}
