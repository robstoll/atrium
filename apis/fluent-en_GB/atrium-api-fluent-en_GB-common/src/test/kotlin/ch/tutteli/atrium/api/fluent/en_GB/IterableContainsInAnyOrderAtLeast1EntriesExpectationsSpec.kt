package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec.Companion as C

class IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec(
        functionDescription to C::containsInAnyOrderEntries,
        (functionDescription to C::containsNullableEntries).withNullableSuffix(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec(
        fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>(Expect<Iterable<Double>>::contains),
        fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>(Expect<Iterable<Double?>>::contains).withNullableSuffix(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$atLeast(1).$entry/$entries"

        private fun containsInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.atLeast(1).entry(a)
            else expect.contains.inAnyOrder.atLeast(1).entries(a, *aX)

        private fun containsNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.atLeast(1).entry(a)
            else expect.contains.inAnyOrder.atLeast(1).entries(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.atLeast(1).entry {}
        nList = nList.contains.inAnyOrder.atLeast(1).entry {}
        subList = subList.contains.inAnyOrder.atLeast(1).entry {}
        star = star.contains.inAnyOrder.atLeast(1).entry {}

        nList = nList.contains.inAnyOrder.atLeast(1).entry(null)
        star = star.contains.inAnyOrder.atLeast(1).entry(null)

        list = list.contains.inAnyOrder.atLeast(1).entries({}, {})
        nList = nList.contains.inAnyOrder.atLeast(1).entries({}, {})
        subList = subList.contains.inAnyOrder.atLeast(1).entries({}, {})
        star = star.contains.inAnyOrder.atLeast(1).entries({}, {})

        nList = nList.contains.inAnyOrder.atLeast(1).entries(null, {}, null)
        star = star.contains.inAnyOrder.atLeast(1).entries(null, {}, null)

        list = list.contains {}
        nList = nList.contains {}
        subList = subList.contains {}
        star = star.contains {}

        nList = nList.contains(null)
        star = star.contains(null)

        list = list.contains({}, {})
        nList = nList.contains({}, {})
        subList = subList.contains({}, {})
        star = star.contains({}, {})

        nList = nList.contains(null, {}, null)
        star = star.contains(null, {}, null)
    }
}
