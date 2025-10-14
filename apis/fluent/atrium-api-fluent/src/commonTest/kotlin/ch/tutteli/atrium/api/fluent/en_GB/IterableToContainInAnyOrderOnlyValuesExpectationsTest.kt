package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToContainInAnyOrderOnlyValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
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

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.only.value(1)
        nList = nList.toContain.inAnyOrder.only.value(1)
        subList = subList.toContain.inAnyOrder.only.value(1)
        star = star.toContain.inAnyOrder.only.value(1)

        list = list.toContain.inAnyOrder.only.values(1, 1.2)
        nList = nList.toContain.inAnyOrder.only.values(1, 1.2)
        subList = subList.toContain.inAnyOrder.only.values(1, 2.2)
        star = star.toContain.inAnyOrder.only.values(1, 1.2, "asdf")

        list = list.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        nList = nList.toContain.inAnyOrder.only.values(1, 1.2, report = {})
        subList = subList.toContain.inAnyOrder.only.values(1, 2.2, report = {})
        star = star.toContain.inAnyOrder.only.values(1, 1.2, "asdf", report = {})

        nList = nList.toContain.inAnyOrder.only.values(null, 1.2)
        star = star.toContain.inAnyOrder.only.values(null, 1.2, "asdf")

        nList = nList.toContain.inAnyOrder.only.values(null, 1.2, report = {})
        star = star.toContain.inAnyOrder.only.values(null, 1.2, "asdf", report = {})
    }
}
