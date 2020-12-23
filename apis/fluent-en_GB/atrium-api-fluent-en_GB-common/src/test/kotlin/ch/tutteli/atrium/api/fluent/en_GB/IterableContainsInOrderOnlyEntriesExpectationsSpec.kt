package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInOrderOnlyEntriesAssertionsSpec.Companion as C

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        functionDescription to C::containsInOrderOnly,
        (functionDescription to C::containsInOrderOnlyNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>(Expect<Iterable<Double>>::containsExactly),
        fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>(Expect<Iterable<Double?>>::containsExactly).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$entry/$entries"

        private fun containsInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.entry(a)
            else expect.contains.inOrder.only.entries(a, *aX)

        private fun containsInOrderOnlyNullable(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.entry(a)
            else expect.contains.inOrder.only.entries(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inOrder.only.entry {}
        nList = nList.contains.inOrder.only.entry {}
        subList = subList.contains.inOrder.only.entry {}
        star = star.contains.inOrder.only.entry {}

        nList = nList.contains.inOrder.only.entry(null)
        star = star.contains.inOrder.only.entry(null)

        list = list.contains.inOrder.only.entries({}, {})
        nList = nList.contains.inOrder.only.entries({}, {})
        subList = subList.contains.inOrder.only.entries({}, {})
        star = star.contains.inOrder.only.entries({}, {})

        nList = nList.contains.inOrder.only.entries(null, {}, null)
        star = star.contains.inOrder.only.entries(null, {}, null)

        list = list.containsExactly {}
        nList = nList.containsExactly {}
        subList = subList.containsExactly {}
        star = star.containsExactly {}

        nList = nList.containsExactly(null)
        star = star.containsExactly(null)

        list = list.containsExactly({}, {})
        nList = nList.containsExactly({}, {})
        subList = subList.containsExactly({}, {})
        star = star.containsExactly({}, {})

        nList = nList.containsExactly(null, {}, null)
        star = star.containsExactly(null, {}, null)
    }
}
