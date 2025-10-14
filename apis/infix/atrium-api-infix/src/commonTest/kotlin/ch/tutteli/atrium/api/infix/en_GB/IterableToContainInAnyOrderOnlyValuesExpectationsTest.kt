package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInAnyOrderOnlyValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
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
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order but only value(1)
        nList = nList toContain o inAny order but only value(1)
        subList = subList toContain o inAny order but only value(1)
        star = star toContain o inAny order but only value(1)

        //TODO type parameter should not be necessary, check with Kotlin 1.4
        list = list toContain o inAny order but only the values<Number>(1, 1.2)
        nList = nList toContain o inAny order but only the values<Number>(1, 1.2)
        subList = subList toContain o inAny order but only the values<Number>(1, 2.2)
        star = star toContain o inAny order but only the values(1, 1.2, "asdf")

        list = list toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        nList = nList toContain o inAny order but only the values(1, 1.2, reportOptionsInAnyOrderOnly = {})
        subList = subList toContain o inAny order but only the values(1, 2.2, reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the values(1, 1.2, "asdf", reportOptionsInAnyOrderOnly = {})

        nList = nList toContain o inAny order but only the values(null, 1.2)
        star = star toContain o inAny order but only the values(null, 1.2, "asdf")

        nList = nList toContain o inAny order but only the values(null, 1.2, reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the values(null, 1.2, "asdf", reportOptionsInAnyOrderOnly = {})
    }
}
