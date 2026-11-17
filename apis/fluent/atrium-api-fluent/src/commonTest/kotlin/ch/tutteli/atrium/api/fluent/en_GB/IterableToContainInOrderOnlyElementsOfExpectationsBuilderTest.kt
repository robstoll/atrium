package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInOrderOnlyElementsOfExpectationsBuilderTest.Companion as C

class IterableToContainInOrderOnlyElementsOfExpectationsBuilderTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        functionDescription to C::toContainElementsOf,
        (functionDescription to C::toContainElementsOfNullable).withNullableSuffix()
    ) {

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory<List<Int>>(
        listOf(1, 2),
        functionDescription to { input -> toContain.inOrder.only.elementsOf(input) }
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$elementsOf"

        private fun toContainElementsOf(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) expect.toContain.inOrder.only.elementsOf(listOf(a, *aX))
            else expect.toContain.inOrder.only.elementsOf(listOf(a, *aX), report = report)

        private fun toContainElementsOfNullable(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit = {}
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) expect.toContain.inOrder.only.elementsOf(sequenceOf(a, *aX))
            else expect.toContain.inOrder.only.elementsOf(listOf(a, *aX), report = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list.toContain.inOrder.only.elementsOf(listOf(1))
        nList = nList.toContain.inOrder.only.elementsOf(listOf(1))
        subList = subList.toContain.inOrder.only.elementsOf(listOf(1))
        star = star.toContain.inOrder.only.elementsOf(listOf(1))

        list = list.toContain.inOrder.only.elementsOf(listOf(1), report = { showAlwaysSummary() })
        nList = nList.toContain.inOrder.only.elementsOf(listOf(1), report = { showOnlyFailing() })
        subList = subList.toContain.inOrder.only.elementsOf(listOf(1), report = { })
        star = star.toContain.inOrder.only.elementsOf(listOf(1), report = { })
    }
}
