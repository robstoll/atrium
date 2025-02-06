package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction4
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.IterableToContainInOrderOnlyEntriesExpectationsSpec.Companion as C

class IterableToContainInOrderOnlyEntriesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        functionDescription to C::toContainInOrderOnly,
        (functionDescription to C::toContainInOrderOnlyNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        shortcutFunctionDescription to C::toContainExactly,
        (shortcutFunctionDescription to C::toContainExactlyNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$entry/$entries"
        private val toContainExactlyFun: KFunction4<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InOrderOnlyReportingOptions.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactly
        val shortcutFunctionDescription = toContainExactlyFun.name

        private fun toContainInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
                else expect.toContain.inOrder.only.entries(a, *aX)
            } else expect.toContain.inOrder.only.entries(a, *aX, report = report)

        private fun toContainInOrderOnlyNullable(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
                else expect.toContain.inOrder.only.entries(a, *aX)
            } else expect.toContain.inOrder.only.entries(a, *aX, report = report)

        private fun toContainExactly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> = expect.toContainExactly(a, *aX, report = report)

        private fun toContainExactlyNullable(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> = expect.toContainExactly(a, *aX, report = report)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.entry {}
        nList = nList.toContain.inOrder.only.entry {}
        subList = subList.toContain.inOrder.only.entry {}
        star = star.toContain.inOrder.only.entry {}

        nList = nList.toContain.inOrder.only.entry(null)
        star = star.toContain.inOrder.only.entry(null)

        list = list.toContain.inOrder.only.entries({}, {})
        nList = nList.toContain.inOrder.only.entries({}, {})
        subList = subList.toContain.inOrder.only.entries({}, {})
        star = star.toContain.inOrder.only.entries({}, {})

        nList = nList.toContain.inOrder.only.entries(null, {}, null)
        star = star.toContain.inOrder.only.entries(null, {}, null)

        list = list.toContain.inOrder.only.entries({}, {}, report = { })
        nList = nList.toContain.inOrder.only.entries({}, {}, report = { })
        subList = subList.toContain.inOrder.only.entries({}, {}, report = { })
        star = star.toContain.inOrder.only.entries({}, {}, report = { })

        nList = nList.toContain.inOrder.only.entries(null, {}, null, report = { })
        star = star.toContain.inOrder.only.entries(null, {}, null, report = { })

        list = list.toContainExactly {}
        nList = nList.toContainExactly {}
        subList = subList.toContainExactly {}
        star = star.toContainExactly {}

        nList = nList.toContainExactly(null)
        star = star.toContainExactly(null)

        list = list.toContainExactly({}, {})
        nList = nList.toContainExactly({}, {})
        subList = subList.toContainExactly({}, {})
        star = star.toContainExactly({}, {})

        list = list.toContainExactly({}, {}, report = { showOnlyFailingIfMoreExpectedElementsThan(20) })
        nList = nList.toContainExactly({}, {}, report = { showOnlyFailing() })
        subList = subList.toContainExactly({}, {}, report = { showAlwaysSummary() })
        star = star.toContainExactly({}, {}, report = { })

        nList = nList.toContainExactly(null, {}, null)
        star = star.toContainExactly(null, {}, null)

        nList = nList.toContainExactly(null, {}, null, report = {})
        star = star.toContainExactly(null, {}, null, report = {})
    }
}
