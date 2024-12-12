package ch.tutteli.atrium.api.infix.en_GB


import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInAnyOrderOnlyEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyEntriesExpectationsSpec(
        getContainsPair(),
        getContainsNullablePair()
    ) {

    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyEntries

        private fun toContainInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only entry a
                else expect toContain o inAny order but only the entries(a, *aX)
            } else expect toContain o inAny order but only the entries(a, *aX, reportOptionsInAnyOrderOnly = report)


        fun getContainsNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::toContainInAnyOrderOnlyNullableEntries

        private fun toContainInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInAnyOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inAny order but only entry a
                else expect toContain o inAny order but only the entries(a, *aX)
            } else expect toContain o inAny order but only the entries(a, *aX, reportOptionsInAnyOrderOnly = report)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order but only entry {}
        nList = nList toContain o inAny order but only entry {}
        subList = subList toContain o inAny order but only entry {}
        star = star toContain o inAny order but only entry {}

        nList = nList toContain o inAny order but only entry (null)
        star = star toContain o inAny order but only entry (null)

        list = list toContain o inAny order but only the entries({}, {})
        nList = nList toContain o inAny order but only the entries({}, {})
        subList = subList toContain o inAny order but only the entries({}, {})
        star = star toContain o inAny order but only the entries({}, {})

        list = list toContain o inAny order but only the entries({}, {}, reportOptionsInAnyOrderOnly = {})
        nList = nList toContain o inAny order but only the entries({}, {}, reportOptionsInAnyOrderOnly = {})
        subList = subList toContain o inAny order but only the entries({}, {}, reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the entries({}, {}, reportOptionsInAnyOrderOnly = {})

        nList = nList toContain o inAny order but only the entries(null, {}, null)
        star = star toContain o inAny order but only the entries(null, {}, null)

        nList = nList toContain o inAny order but only the entries(null, {}, null, reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the entries(null, {}, null, reportOptionsInAnyOrderOnly = {})
    }
}
