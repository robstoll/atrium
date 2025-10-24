package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
        getContainsPair(),
        getContainsNullablePair()
    ) {

    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::toContainInAnyOrderOnlyValues

        private fun toContainInAnyOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only value a
                else expect toContain o inAny order but only the values(a, *aX)
            } else expect toContain o inAny order but only the values(a, *aX, reportOptionsInAnyOrderOnly = report)


        fun getContainsNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::toContainInAnyOrderOnlyNullableValues

        private fun toContainInAnyOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only value a
                else expect toContain o inAny order but only the values(a, *aX)
            } else expect toContain o inAny order but only the values(a, *aX, reportOptionsInAnyOrderOnly = report)
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

        list = list toContain o inAny order but only value 1
        nSet = nSet toContain o inAny order but only value 1
        nCollection = nCollection toContain o inAny order but only value null
        subList = subList toContain o inAny order but only value 1
        starSet = starSet toContain o inAny order but only value 1
        starCollection = starCollection toContain o inAny order but only value null

        listValues = listValues toContain o inAny order but only the values(1, 1.2)
        nSetValues = nSetValues toContain o inAny order but only the values(1, 1.2)
        nCollectionValues = nCollectionValues toContain o inAny order but only the values(null, 1.2)
        subListValues = subListValues toContain o inAny order but only the values(1, 1.2)
        starSetValues = starSetValues toContain o inAny order but only the values(1, 1.2, "asdf")
        starCollectionValues = starCollectionValues toContain o inAny order but only the values(1, null, "asdf")

        listValues = listValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        nSetValues = nSetValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        subListValues = subListValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        nCollectionValues = nCollectionValues toContain o inAny order but only the values(null, 1.2, reportOptionsInAnyOrderOnly = {})
        starSetValues = starSetValues toContain o inAny order but only the values(1, 1.2, "asdf", reportOptionsInAnyOrderOnly = {})
        starCollectionValues = starCollectionValues toContain o inAny order but only the values(1, null, "asdf", reportOptionsInAnyOrderOnly = {})
    }
}
