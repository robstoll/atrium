package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInOrderOnlyValuesExpectationsTest
import ch.tutteli.atrium.specs.integration.utils.iterableLikeToIterableTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInOrderOnlyElementsOfExpectationsShortcutTest.Companion as C

class IterableToContainInOrderOnlyElementsOfExpectationsShortcutTest :
    AbstractIterableToContainInOrderOnlyValuesExpectationsTest(
        shortcutDescription to C::toContainExactlyElementsOfShortcut,
        (shortcutDescription to C::toContainExactlyElementsOfNullableShortcut).withNullableSuffix()
    ) {

    @TestFactory
    fun iterableLikeToIterableTest() = iterableLikeToIterableTestFactory<List<Int>>(
        listOf(1, 2),
        shortcutDescription to { input -> toContainExactlyElementsOf(input) }
    )

    companion object : IterableToContainSpecBase() {
        val shortcutDescription = Expect<Iterable<Int>>::toContainExactlyElementsOf.name

        private fun toContainExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit = {}
        ): Expect<Iterable<Double>> = expect.toContainExactlyElementsOf(arrayOf(a, *aX), report)

        private fun toContainExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit = {}
        ): Expect<Iterable<Double?>> = expect.toContainExactlyElementsOf(sequenceOf(a, *aX).asIterable(), report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list.toContainExactlyElementsOf(listOf(1))
        nList = nList.toContainExactlyElementsOf(listOf(1))
        subList = subList.toContainExactlyElementsOf(listOf(1))
        star = star.toContainExactlyElementsOf(listOf(1))

        var listValues: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nListValues: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subListValues: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2.2))
        var subListOne: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var starValues: Expect<Collection<*>> = expect(listOf(1, 1.2, "asdf"))

        listValues = listValues.toContainExactlyElementsOf(listOf(1, 1.2), report = { })
        nListValues = nListValues.toContainExactlyElementsOf(listOf(1, 1.2), report = { })
        subListValues = subListValues.toContainExactlyElementsOf(listOf(1, 2.2), report = { })
        subListOne = subListOne.toContainExactlyElementsOf(listOf(1), report = { })
        starValues = starValues.toContainExactlyElementsOf(listOf(1, 1.2, "asdf"), report = { })
    }
}
