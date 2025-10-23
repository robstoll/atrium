package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInAnyOrderOnlyElementsOfExpectationsTest.Companion as C

class IterableToContainInAnyOrderOnlyElementsOfExpectationsTest :
    AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
        functionDescription to C::toContainElementsOf,
        (functionDescription to C::toContainElementsOfNullable).withNullableSuffix()
    ) {

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory<List<Int>>(
        listOf(1, 2),
        functionDescription to { input -> toContain.inAnyOrder.only.elementsOf(input) }
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$elementsOf"

        private fun toContainElementsOf(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inAnyOrder.only.elementsOf(listOf(a, *aX))
            } else expect.toContain.inAnyOrder.only.elementsOf(listOf(a, *aX), report = report)

        private fun toContainElementsOfNullable(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report == emptyInAnyOrderOnlyReportOptions) {
                expect.toContain.inAnyOrder.only.elementsOf(listOf(a, *aX))
            } else expect.toContain.inAnyOrder.only.elementsOf(listOf(a, *aX), report = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var nCollection: Expect<Set<Number?>> = expect(setOf(null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2))
        var starSet: Expect<Set<*>> = expect(setOf(1, 1.2, "asdf"))
        var starCollection: Expect<Collection<*>> = expect(listOf(1, null, "asdf"))

        list = list.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2))
        nSet = nSet.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2))
        nCollection = nCollection.toContain.inAnyOrder.only.elementsOf(listOf(null, 1.2))
        subList = subList.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2))
        starSet = starSet.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2, "asdf"))
        starCollection = starCollection.toContain.inAnyOrder.only.elementsOf(listOf(1, null, "asdf"))

        list = list.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2), report = {})
        nSet = nSet.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2), report = {})
        subList = subList.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2), report = {})
        nCollection = nCollection.toContain.inAnyOrder.only.elementsOf(listOf(null, 1.2), report = {})
        starSet = starSet.toContain.inAnyOrder.only.elementsOf(listOf(1, 1.2, "asdf"), report = {})
        starCollection = starCollection.toContain.inAnyOrder.only.elementsOf(listOf(1, null, "asdf"), report = {})
    }
}
