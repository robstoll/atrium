package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsNotEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsNotEntriesExpectationsSpec(
        functionDescription to Companion::containsNotFun,
        (functionDescription to Companion::containsNotNullableFun).withNullableSuffix(),
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        private val functionDescription = "$containsNot.$entry/$entries"

        private fun containsNotFun(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.containsNot.entry(a)
            else expect.containsNot.entries(a, *aX)

        private fun containsNotNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.containsNot.entry(a)
            else expect.containsNot.entries(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.containsNot.entry {}
        nList = nList.containsNot.entry {}
        subList = subList.containsNot.entry {}
        star = star.containsNot.entry {}

        nList = nList.containsNot.entry(null)
        star = star.containsNot.entry(null)

        list = list.containsNot.entries({}, {})
        nList = nList.containsNot.entries({}, {})
        subList = subList.containsNot.entries({}, {})
        star = star.containsNot.entries({}, {})

        nList = nList.containsNot.entries(null, {}, null)
        star = star.containsNot.entries(null, {}, null)
    }
}
