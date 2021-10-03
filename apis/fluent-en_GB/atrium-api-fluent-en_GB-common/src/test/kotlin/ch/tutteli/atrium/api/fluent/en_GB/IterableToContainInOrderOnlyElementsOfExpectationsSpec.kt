package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInOrderOnlyElementsOfExpectationsSpec.Companion as C

class IterableToContainInOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::toContainInOrderOnlyValues,
        (functionDescription to C::toContainInOrderOnlyNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        shortcutDescription to C::toContainExactlyElementsOfShortcut,
        (shortcutDescription to C::toContainExactlyElementsOfNullableShortcut).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            functionDescription,
            listOf(1, 2),
            { input -> toContain.inOrder.only.elementsOf(input) }
        )

    object ShortcutIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            shortcutDescription,
            listOf(1, 2),
            { input -> toContainExactlyElementsOf(input) }
        )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$elementsOf"
        val shortcutDescription = Expect<Iterable<Int>>::toContainExactlyElementsOf.name

        fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) expect.toContain.inOrder.only.elementsOf(listOf(a, *aX))
            else expect.toContain.inOrder.only._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit = {}
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) expect.toContain.inOrder.only.elementsOf(sequenceOf(a, *aX))
            else expect.toContain.inOrder.only._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

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


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.elementsOf(listOf<Int>())
        nList = nList.toContain.inOrder.only.elementsOf(listOf<Int>())
        subList = subList.toContain.inOrder.only.elementsOf(listOf<Int>())
        star = star.toContain.inOrder.only.elementsOf(listOf<Int>())
        //TODO use the following with 0.18.0
//        list = list.toContain.inOrder.only.elementsOf(listOf<Int>(), report = { showAlwaysSummary() })
//        nList = nList.toContain.inOrder.only.elementsOf(listOf<Int>(), report = { showOnlyFailing() })
//        subList = subList.toContain.inOrder.only.elementsOf(listOf<Int>(), report = { })
//        star = star.toContain.inOrder.only.elementsOf(listOf<Int>(), report = { })

        list = list.toContainExactlyElementsOf(1, report = { })
        nList = nList.toContainExactlyElementsOf(1, report = { })
        subList = subList.toContainExactlyElementsOf(1, report = { })
        star = star.toContainExactlyElementsOf(1, report = { })

        list = list.toContainExactlyElementsOf(listOf(1, 1.2), report = { })
        nList = nList.toContainExactlyElementsOf(listOf(1, 1.2), report = { })
        subList = subList.toContainExactlyElementsOf(listOf(1, 2.2), report = { })
        subList = subList.toContainExactlyElementsOf(listOf(1), report = { })
        star = star.toContainExactlyElementsOf(listOf(1, 1.2, "asdf"), report = { })
    }
}

