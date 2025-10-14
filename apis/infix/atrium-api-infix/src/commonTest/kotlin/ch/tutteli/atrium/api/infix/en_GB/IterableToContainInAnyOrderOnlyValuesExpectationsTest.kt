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


    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        var listValues:Expect<List<Number>> = expect(listOf(1, 1.2))
        var nListValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1, 1.2))
        var starValues: Expect<Collection<*>> = expect(listOf(1, 1.2))

        list = list toContain o inAny order but only value(1)
        nList = nList toContain o inAny order but only value(1)
        subList = subList toContain o inAny order but only value(1)
        star = star toContain o inAny order but only value(1)

        listValues = listValues toContain o inAny order but only the values(1, 1.2)
        nListValues = nListValues toContain o inAny order but only the values(1, 1.2)
        subListValues = subListValues toContain o inAny order but only the values(1, 1.2)
        starValues = starValues toContain o inAny order but only the values(1, 1.2, "asdf")

        listValues = listValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        nListValues = nListValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        subListValues = subListValues toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        starValues = starValues toContain o inAny order but only the values(1, 1.2, "asdf", reportOptionsInAnyOrderOnly = {})

        nListValues = nListValues toContain o inAny order but only the values(null, 1.2)
        starValues = starValues toContain o inAny order but only the values(null, 1.2, "asdf")

        nListValues = nListValues toContain o inAny order but only the values(null, 1.2, reportOptionsInAnyOrderOnly = {})
        starValues = starValues toContain o inAny order but only the values(null, 1.2, "asdf", reportOptionsInAnyOrderOnly = {})
    }
}
