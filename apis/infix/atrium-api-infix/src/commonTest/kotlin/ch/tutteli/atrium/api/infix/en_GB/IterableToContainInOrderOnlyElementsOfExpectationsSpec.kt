package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContain o inGiven order and only elementsOf",
            listOf(1, 2),
            { input -> it toContain o inGiven order and only elementsOf input }
        )

    object ShortcutIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContainExactlyElementsOf",
            listOf(1, 2),
            { input -> it toContainExactlyElementsOf input }
        )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyValues

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf listOf(a, *aX)
            else expect toContain o inGiven order and only the elementsOf(listOf(a, *aX), reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf sequenceOf(a, *aX)
            else expect toContain o inGiven order and only the elementsOf(listOf(a, *aX), reportOptionsInOrderOnly = report)

        private val toContainExactlyElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactlyElementsOf

        private fun getToContainShortcutPair() =
            toContainExactlyElementsOfShortcutFun.name to Companion::toContainExactlyElementsOfShortcut

        private fun toContainExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions)  expect toContainExactlyElementsOf arrayOf(a, *aX)
            else expect toContainExactly elementsOf(arrayOf(a, *aX), report)

        private val toContainExactlyElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactlyElementsOf

        private fun getToContainNullableShortcutPair() =
            toContainExactlyElementsOfNullableShortcutFun.name to Companion::toContainExactlyElementsOfNullableShortcut;

        private fun toContainExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) expect toContainExactlyElementsOf sequenceOf(a, *aX).asIterable()
            else expect toContainExactly elementsOf(arrayOf(a, *aX), report)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only elementsOf(emptyList<Int>())
        nList = nList toContain o inGiven order and only elementsOf(emptyList<Int>())
        subList = subList toContain o inGiven order and only elementsOf(emptyList<Int>())
        star = star toContain o inGiven order and only elementsOf(emptyList<Int>())

        list = list toContain o inGiven order and only the elementsOf(emptyList<Int>(), reportOptionsInOrderOnly = { showAlwaysSummary() })
        nList = nList toContain o inGiven order and only the elementsOf(emptyList<Int>(), reportOptionsInOrderOnly = { showOnlyFailing()})
        subList = subList toContain o inGiven order and only the elementsOf(emptyList<Int>(), reportOptionsInOrderOnly = {})
        star = star toContain o inGiven order and only the elementsOf(emptyList<Int>(), reportOptionsInOrderOnly = {})

        list = list.toContainExactlyElementsOf(listOf(1))
        nList = nList.toContainExactlyElementsOf(listOf(1))
        subList = subList.toContainExactlyElementsOf(listOf(1))
        star = star.toContainExactlyElementsOf(listOf(1))
    }
}

