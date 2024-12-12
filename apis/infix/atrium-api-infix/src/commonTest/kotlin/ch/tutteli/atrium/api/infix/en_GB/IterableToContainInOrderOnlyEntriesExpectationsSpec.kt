package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
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
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else expect toContain o inGiven order and only the entries(a, *aX, reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::toContainInOrderOnlyNullableEntriesPair

        private fun toContainInOrderOnlyNullableEntriesPair(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only entry a
                else expect toContain o inGiven order and only the entries(a, *aX)
            } else expect toContain o inGiven order and only the entries(a, *aX, reportOptionsInOrderOnly = report)

        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyEntriesShortcut

        private fun toContainInOrderOnlyEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly { a() }
                else expect toContainExactly entries(a, *aX)
            } else expect toContainExactly entries(a, *aX, reportOptionsInOrderOnly = report)

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
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) {
                    //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
                    if (a == null) expect toContainExactly a as Double?
                    else expect toContainExactly { a() }
                } else {
                    expect toContainExactly entries(a, *aX)
                }
            } else expect toContainExactly entries(a, *aX, reportOptionsInOrderOnly = report)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only entry {}
        nList = nList toContain o inGiven order and only entry {}
        subList = subList toContain o inGiven order and only entry {}
        star = star toContain o inGiven order and only entry {}

        nList = nList toContain o inGiven order and only entry (null)
        star = star toContain o inGiven order and only entry (null)

        list = list toContain o inGiven order and only the entries({}, {})
        nList = nList toContain o inGiven order and only the entries({}, {})
        subList = subList toContain o inGiven order and only the entries({}, {})
        star = star toContain o inGiven order and only the entries({}, {})

        nList toContain o inGiven order and only the entries(null, {})
        star toContain o inGiven order and only the entries(null, {})

        nList toContain o inGiven order and only the entries({}, null)
        star toContain o inGiven order and only the entries({}, null)

        nList toContain o inGiven order and only the entries(null, null)
        star toContain o inGiven order and only the entries(null, null)

        nList = nList toContain o inGiven order and only the entries(null, {}, null)
        star = star toContain o inGiven order and only the entries(null, {}, null)

        list = list toContain o inGiven order and only the entries({}, {}, reportOptionsInOrderOnly = { })
        nList = nList toContain o inGiven order and only the entries({}, {}, reportOptionsInOrderOnly = { })
        subList = subList toContain o inGiven order and only the entries({}, {}, reportOptionsInOrderOnly = { })
        star = star toContain o inGiven order and only the entries({}, {}, reportOptionsInOrderOnly = { })

        nList = nList toContain o inGiven order and only the entries(null, {}, null, reportOptionsInOrderOnly = { })
        star = star toContain o inGiven order and only the entries(null, {}, null, reportOptionsInOrderOnly = { })

        list = list toContainExactly {}
        nList = nList toContainExactly {}
        subList = subList toContainExactly {}
        star = star toContainExactly {}

        nList = nList toContainExactly null
        star = star toContainExactly null

        list = list toContainExactly entries({}, {})
        nList = nList toContainExactly entries({}, {})
        subList = subList toContainExactly entries({}, {})
        star = star toContainExactly entries({}, {})

        list = list toContainExactly entries(
            {},
            {},
            reportOptionsInOrderOnly = { showOnlyFailingIfMoreExpectedElementsThan(20) }
        )
        nList = nList toContainExactly entries({}, {}, reportOptionsInOrderOnly = { showOnlyFailing() })
        subList = subList toContainExactly entries({}, {}, reportOptionsInOrderOnly = { showAlwaysSummary() })
        star = star toContainExactly entries({}, {}, reportOptionsInOrderOnly = { })

        nList = nList toContainExactly entries(null, {})
        star = star toContainExactly entries(null, {})

        nList = nList toContainExactly entries({}, null)
        star = star toContainExactly entries({}, null)

        nList = nList toContainExactly entries(null, null)
        star = star toContainExactly entries(null, null)

        nList = nList toContainExactly entries(null, {}, null)
        star = star toContainExactly entries(null, {}, null)

        nList = nList toContainExactly entries(null, {}, null, reportOptionsInOrderOnly = {})
        star = star toContainExactly entries(null, {}, null, reportOptionsInOrderOnly = {})
    }
}
