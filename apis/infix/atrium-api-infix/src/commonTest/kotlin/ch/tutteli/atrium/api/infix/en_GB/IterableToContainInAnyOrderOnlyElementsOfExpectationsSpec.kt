package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class IterableToContainInAnyOrderOnlyElementsOfExpectationsSpec : Spek({
//    include(BuilderSpec)
    include(BuilderIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair()
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContain o inAny order but only elementsOf",
            listOf(1, 2),
            { input -> it toContain o inAny order but only elementsOf input }
        )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getToContainValues

        private fun getToContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inAny order but only elementsOf listOf(a, *aX)
            } else {
                expect toContain o inAny order but only the elementsOf(
                    listOf(a, *aX),
                    reportOptionsInAnyOrderOnly = report
                )
            }

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf" to Companion::getToContainNullableValues

        private fun getToContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inAny order but only elementsOf listOf(a, *aX)
            } else {
                expect toContain o inAny order but only the elementsOf(
                    listOf(a, *aX),
                    reportOptionsInAnyOrderOnly = report
                )
            }
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order but only elementsOf emptyList<Int>()
        nList = nList toContain o inAny order but only elementsOf emptyList<Int>()
        subList = subList toContain o inAny order but only elementsOf emptyList<Int>()
        star = star toContain o inAny order but only elementsOf emptyList<Int>()

        list = list toContain o inAny order but only the elementsOf(emptyList<Int>(), reportOptionsInAnyOrderOnly = {})
        nList = nList toContain o inAny order but only the elementsOf(emptyList<Int>(), reportOptionsInAnyOrderOnly = {})
        subList =
            subList toContain o inAny order but only the elementsOf(emptyList<Int>(), reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the elementsOf(emptyList<Int>(), reportOptionsInAnyOrderOnly = {})

        nList = nList toContain o inAny order but only elementsOf emptyList<Int?>()
        star = star toContain o inAny order but only elementsOf emptyList<Int?>()

        nList = nList toContain o inAny order but only the elementsOf(emptyList<Int?>(), reportOptionsInAnyOrderOnly = {})
        star = star toContain o inAny order but only the elementsOf(emptyList<Int?>(), reportOptionsInAnyOrderOnly = {})
    }
}
