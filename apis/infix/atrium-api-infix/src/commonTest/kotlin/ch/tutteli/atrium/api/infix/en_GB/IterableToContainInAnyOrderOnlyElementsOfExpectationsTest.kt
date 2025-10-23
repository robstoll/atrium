package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.Number
import kotlin.test.Test

class IterableToContainInAnyOrderOnlyElementsOfExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
        functionDescription to Companion::getToContainValues,
        (functionDescription to Companion::getToContainNullableValues).withNullableSuffix(),
    ) {

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory(
        listOf(1, 2),
        functionDescription to { input -> it toContain o inAny order but only elementsOf input }
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain $filler $inAnyOrder $butOnly $inAnyOrderOnlyElementsOf"

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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var nCollection: Expect<Set<Number?>> = expect(setOf(null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1, 1.2))
        var starSet: Expect<Set<*>> = expect(setOf(1, 1.2, "asdf"))
        var starCollection: Expect<Collection<*>> = expect(listOf(1, null, "asdf"))

        list = list toContain o inAny order but only elementsOf listOf(1, 1.2)
        nSet = nSet toContain o inAny order but only elementsOf listOf(1, 1.2)
        nCollection = nCollection toContain o inAny order but only elementsOf listOf(null, 1.2)
        subList = subList toContain o inAny order but only elementsOf listOf(1, 1.2)
        starSet = starSet toContain o inAny order but only elementsOf listOf(1, 1.2, "asdf")
        starCollection = starCollection toContain o inAny order but only elementsOf listOf(1, null, "asdf")

        list = list toContain o inAny order but only the elementsOf(listOf(1, 1.2), reportOptionsInAnyOrderOnly = {})
        nSet = nSet toContain o inAny order but only the elementsOf(listOf(1, 1.2), reportOptionsInAnyOrderOnly = {})
        subList =
            subList toContain o inAny order but only the elementsOf(listOf(1, 1.2), reportOptionsInAnyOrderOnly = {})
        nCollection = nCollection toContain o inAny order but only the elementsOf(
            listOf(null, 1.2),
            reportOptionsInAnyOrderOnly = {})
        starSet = starSet toContain o inAny order but only the elementsOf(
            listOf(1, 1.2, "asdf"),
            reportOptionsInAnyOrderOnly = {})
        starCollection = starCollection toContain o inAny order but only the elementsOf(
            listOf(1, null, "asdf"),
            reportOptionsInAnyOrderOnly = {})
    }
}
