package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
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
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf listOf(a, *aX)
            else (expect toContain o inGiven order and only)._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) expect toContain o inGiven order and only elementsOf sequenceOf(a, *aX)
            else (expect toContain o inGiven order and only)._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

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
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions)  expect toContainExactlyElementsOf arrayOf(a, *aX)
            else (expect toContain o inGiven order and only)._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

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
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) expect toContainExactlyElementsOf sequenceOf(a, *aX).asIterable()
            else (expect toContain o inGiven order and only)._logicAppend { valuesInOrderOnly(listOf(a, *aX), report) }

    }
}

