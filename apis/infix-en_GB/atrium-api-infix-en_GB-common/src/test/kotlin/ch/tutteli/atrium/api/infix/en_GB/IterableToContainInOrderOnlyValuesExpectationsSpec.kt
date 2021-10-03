package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInOrderOnlyValuesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

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

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::toContainInOrderOnlyValues

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend {
                valuesInOrderOnly(listOf(a, *aX), report)
            }

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend {
                valuesInOrderOnly(listOf(a, *aX), report)
            }

        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyValuesShortcut

        private fun toContainInOrderOnlyValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend {
                valuesInOrderOnly(listOf(a, *aX), report)
            }

        private val toContainNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactly

        fun getToContainNullableShortcutPair() =
            toContainNullableShortcutFun.name to Companion::toContainInOrderOnlyNullableValuesShortcut

        private fun toContainInOrderOnlyNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend {
                valuesInOrderOnly(listOf(a, *aX), report)
            }
    }
}

