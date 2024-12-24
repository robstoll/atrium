package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInOrderOnlyValuesExpectationsSpec.Companion as C

class IterableToContainInOrderOnlyValuesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::toContainInOrderOnlyValues,
        (functionDescription to C::toContainInOrderOnlyNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        fun3<Iterable<Double>, Double, Array<out Double>, InOrderOnlyReportingOptions.() -> Unit>(Expect<Iterable<Double>>::toContainExactly),
        fun3<Iterable<Double?>, Double?, Array<out Double?>, InOrderOnlyReportingOptions.() -> Unit>(Expect<Iterable<Double?>>::toContainExactly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$value/$values"

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
                else expect.toContain.inOrder.only.values(a, *aX)
            } else expect.toContain.inOrder.only.values(a, *aX, report = report)

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
                else expect.toContain.inOrder.only.values(a, *aX)
            } else expect.toContain.inOrder.only.values(a, *aX, report = report)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.toContain.inOrder.only.value(1)
        nList = nList.toContain.inOrder.only.value(1)
        subList = subList.toContain.inOrder.only.value(1)
        star = star.toContain.inOrder.only.value(1)

        list = list.toContain.inOrder.only.values(1, 1.2)
        nList = nList.toContain.inOrder.only.values(1, 1.2)
        subList = subList.toContain.inOrder.only.values(1, 2.2)
        star = star.toContain.inOrder.only.values(1, 1.2, "asdf")

        list = list.toContain.inOrder.only.values(1, 1.2, report = {})
        nList = nList.toContain.inOrder.only.values(1, 1.2, report = {})
        subList = subList.toContain.inOrder.only.values(1, 2.2, report = {})
        star = star.toContain.inOrder.only.values(1, 1.2, "asdf", report = {})

        nList = nList.toContain.inOrder.only.values(null, 1.2)
        star = star.toContain.inOrder.only.values(null, 1.2, "asdf")

        nList = nList.toContain.inOrder.only.values(null, 1.2, report = {})
        star = star.toContain.inOrder.only.values(null, 1.2, "asdf", report = {})

        list = list.toContainExactly(1)
        nList = nList.toContainExactly(1, 1.2)
        subList = subList.toContainExactly(1)
        star = star.toContainExactly(1, "a", null)

        list = list.toContainExactly(1, report = { showOnlyFailingIfMoreExpectedElementsThan(1) })
        nList = nList.toContainExactly(1, 1.2, report = { showOnlyFailing() })
        subList = subList.toContainExactly(1, 2.2, report = { showAlwaysSummary() })
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.toContainExactly("asdf", report = {})
        star = star.toContainExactly(1, "asdf", null, report = {})
    }
}

