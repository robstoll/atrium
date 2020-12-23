package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsInAnyOrderOnlyEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyEntriesExpectationsSpec(
         functionDescription to Companion::containsInAnyOrderOnlyEntries,
        (functionDescription to Companion::containsInAnyOrderOnlyNullableEntries).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» "
    ) {
    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$only.$entry/$entries"

        private fun containsInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)

        private fun containsInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.only.entry {}
        nList = nList.contains.inAnyOrder.only.entry {}
        subList = subList.contains.inAnyOrder.only.entry {}
        star = star.contains.inAnyOrder.only.entry {}

        nList = nList.contains.inAnyOrder.only.entry(null)
        star = star.contains.inAnyOrder.only.entry(null)

        list = list.contains.inAnyOrder.only.entries({}, {})
        nList = nList.contains.inAnyOrder.only.entries({}, {})
        subList = subList.contains.inAnyOrder.only.entries({}, {})
        star = star.contains.inAnyOrder.only.entries({}, {})

        nList = nList.contains.inAnyOrder.only.entries(null, {}, null)
        star = star.contains.inAnyOrder.only.entries(null, {}, null)
    }
}
