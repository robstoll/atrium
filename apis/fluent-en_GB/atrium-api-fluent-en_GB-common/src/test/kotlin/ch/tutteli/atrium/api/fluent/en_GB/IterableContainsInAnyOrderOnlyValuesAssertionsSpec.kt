package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsInAnyOrderOnlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
        functionDescription to Companion::containsInAnyOrderOnlyValues,
        (functionDescription to Companion::containsInAnyOrderOnlyNullableValues).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ "
    ) {
    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$only.$value/$values"

        private fun containsInAnyOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.value(a)
            else expect.contains.inAnyOrder.only.values(a, *aX)

        private fun containsInAnyOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.value(a)
            else expect.contains.inAnyOrder.only.values(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.only.value(1)
        nList = nList.contains.inAnyOrder.only.value(1)
        subList = subList.contains.inAnyOrder.only.value(1)
        star = star.contains.inAnyOrder.only.value(1)

        list = list.contains.inAnyOrder.only.values(1, 1.2)
        nList = nList.contains.inAnyOrder.only.values(1, 1.2)
        subList = subList.contains.inAnyOrder.only.values(1, 2.2)
        star = star.contains.inAnyOrder.only.values(1, 1.2, "asdf")
    }
}
