package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInOrderOnlyEntriesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::toContainInOrderOnly

        private fun toContainInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend { entriesInOrderOnly(listOf(a, *aX), report) }

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::toContainInOrderOnlyNullableEntriesPair

        private fun toContainInOrderOnlyNullableEntriesPair(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend { entriesInOrderOnly(listOf(a, *aX), report) }

        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyEntriesShortcut

        private fun toContainInOrderOnlyEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly { a() }
                else expect toContainExactly entries(a, *aX)
            } else (expect toContain o inGiven order and only)._logicAppend { entriesInOrderOnly(listOf(a, *aX), report) }

        private val toContainNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactly

        fun getToContainNullableShortcutPair() =
            toContainNullableShortcutFun.name to Companion::toContainInOrderOnlyNullableEntriesShortcut

        private fun toContainInOrderOnlyNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            //TODO 0.18.0 remove if once implemented
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) {
                    //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
                    if (a == null) expect toContainExactly a as Double?
                    else expect toContainExactly { a() }
                } else {
                    expect toContainExactly entries(a, *aX)
                }
            } else (expect toContain o inGiven order and only)._logicAppend { entriesInOrderOnly(listOf(a, *aX), report) }
    }
}
