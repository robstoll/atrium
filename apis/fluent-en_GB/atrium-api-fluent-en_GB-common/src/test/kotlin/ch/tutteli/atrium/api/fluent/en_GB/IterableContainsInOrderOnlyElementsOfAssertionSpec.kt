package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.kbox.glue
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeSpec)
    include(ShortcutIterableLikeSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeSpec : ch.tutteli.atrium.specs.integration.IterableLikeSpec<List<Int>>(
        "contains.inOrder.only.elementsOf",
        listOf(1, 2),
        { input -> contains.inOrder.only.elementsOf(input) },
        { input -> contains.inOrder.only.elementsOf(input) }
    )

    object ShortcutIterableLikeSpec : ch.tutteli.atrium.specs.integration.IterableLikeSpec<List<Int>>(
        "containsExactlyElementsOf",
        listOf(1, 2),
        { input -> containsExactlyElementsOf(input) },
        { input -> containsExactlyElementsOf(input) }
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        private val containsExactlyElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactlyElementsOf

        private fun getContainsShortcutPair() =
            Pair(containsExactlyElementsOfShortcutFun.name, Companion::containsExactlyElementsOfShortcut)

        private fun containsExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsExactlyElementsOf(a.glue(aX))

        private val containsExactlyElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactlyElementsOf

        private fun getContainsNullableShortcutPair() = Pair(
            containsExactlyElementsOfNullableShortcutFun.name,
            Companion::containsExactlyElementsOfNullableShortcut
        )

        private fun containsExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsExactlyElementsOf(a.glue(aX))

    }
}

