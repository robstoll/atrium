package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
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
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else expect toContain o inGiven order and only the values(a, *aX, reportOptionsInOrderOnly = report)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContain o inGiven order and only value a
                else expect toContain o inGiven order and only the values(a, *aX)
            } else expect toContain o inGiven order and only the values(a, *aX, reportOptionsInOrderOnly = report)

        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInOrderOnlyValuesShortcut

        private fun toContainInOrderOnlyValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else expect toContainExactly values(a, *aX, reportOptionsInOrderOnly = report)

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
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect toContainExactly a
                else expect toContainExactly values(a, *aX)
            } else expect toContainExactly values(a, *aX, reportOptionsInOrderOnly = report)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only value 1
        nList = nList toContain o inGiven order and only value 1
        subList = subList toContain o inGiven order and only value 1
        star = star toContain o inGiven order and only value 1

        //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
        list = list toContain o inGiven order and only the values<Number>(1, 1.2)
        nList = nList toContain o inGiven order and only the values<Number?>(1, 1.2)
        subList = subList toContain o inGiven order and only the values<Number>(1, 2.2)
        star = star toContain o inGiven order and only the values<Any?>(1, 1.2, "asdf")

        list = list toContain o inGiven order and only the values(1, 1.2, reportOptionsInOrderOnly = {})
        nList = nList toContain o inGiven order and only the values(1, 1.2, reportOptionsInOrderOnly = {})
        subList = subList toContain o inGiven order and only the values(1, 2.2, reportOptionsInOrderOnly = {})
        star = star toContain o inGiven order and only the values(1, 1.2, "asdf", reportOptionsInOrderOnly = {})

        list = list toContainExactly 1
        nList = nList toContainExactly values(1, 1.2)
        subList = subList toContainExactly 1
        star = star toContainExactly 1

        //TODO we should actually setup proper tests for those cases as we only see it compiles (has no ambiguity)
        // but don't know if it has chosen the correct overload in the end
        // (because we sometimes have Any as param and passing a ParamObject to such a function is not a compile error)
        list = list toContainExactly values(1, 1.2, reportOptionsInOrderOnly = {
            showOnlyFailingIfMoreExpectedElementsThan(1)
        })
        nList = nList toContainExactly values(1, 1.2, reportOptionsInOrderOnly = { showOnlyFailing() })
        subList = subList toContainExactly values(1, 2.2, reportOptionsInOrderOnly = { showAlwaysSummary() })
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList toContainExactly values("asdf", reportOptionsInOrderOnly = {})
        star = star toContainExactly values(1, 1.2, "asdf", reportOptionsInOrderOnly = {})
    }
}

