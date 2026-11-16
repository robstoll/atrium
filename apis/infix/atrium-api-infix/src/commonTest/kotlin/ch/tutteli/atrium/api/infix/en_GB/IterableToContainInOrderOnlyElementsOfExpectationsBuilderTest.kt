package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class IterableToContainInOrderOnlyElementsOfExpectationsBuilderTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair()
    ) {

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory<List<Int>>(
        listOf(1, 2),
        "toContain o inGiven order and only elementsOf" to { input ->
            it toContain o inGiven order and only elementsOf input
        }
    )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyElementsOf

        private fun toContainInOrderOnlyElementsOf(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf listOf(a, *aX)
            else expect toContain o inGiven order and only the elementsOf(listOf(a, *aX), reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyNullableElementsOf

        private fun toContainInOrderOnlyNullableElementsOf(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf sequenceOf(a, *aX)
            else expect toContain o inGiven order and only the elementsOf(listOf(a, *aX), reportOptionsInOrderOnly = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list toContain o inGiven order and only elementsOf(listOf(1))
        nList = nList toContain o inGiven order and only elementsOf(listOf(1))
        subList = subList toContain o inGiven order and only elementsOf(listOf(1))
        star = star toContain o inGiven order and only elementsOf(listOf(1))

        list = list toContain o inGiven order and only the elementsOf(listOf(1), reportOptionsInOrderOnly = { showAlwaysSummary() })
        nList = nList toContain o inGiven order and only the elementsOf(listOf(1), reportOptionsInOrderOnly = { showOnlyFailing() })
        subList = subList toContain o inGiven order and only the elementsOf(listOf(1), reportOptionsInOrderOnly = {})
        star = star toContain o inGiven order and only the elementsOf(listOf(1), reportOptionsInOrderOnly = {})
    }
}
