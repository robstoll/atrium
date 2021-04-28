package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsInAnyOrderOnlyEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyEntriesExpectationsSpec(
        functionDescription to Companion::toContainInAnyOrderOnlyEntries,
        (functionDescription to Companion::toContainInAnyOrderOnlyNullableEntries).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$entry/$entries"

        private fun toContainInAnyOrderOnlyEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
            else expect.toContain.inAnyOrder.only.entries(a, *aX)

        private fun toContainInAnyOrderOnlyNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
            else expect.toContain.inAnyOrder.only.entries(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.only.entry {}
        nList = nList.toContain.inAnyOrder.only.entry {}
        subList = subList.toContain.inAnyOrder.only.entry {}
        star = star.toContain.inAnyOrder.only.entry {}

        nList = nList.toContain.inAnyOrder.only.entry(null)
        star = star.toContain.inAnyOrder.only.entry(null)

        list = list.toContain.inAnyOrder.only.entries({}, {})
        nList = nList.toContain.inAnyOrder.only.entries({}, {})
        subList = subList.toContain.inAnyOrder.only.entries({}, {})
        star = star.toContain.inAnyOrder.only.entries({}, {})

        nList = nList.toContain.inAnyOrder.only.entries(null, {}, null)
        star = star.toContain.inAnyOrder.only.entries(null, {}, null)
    }
}
