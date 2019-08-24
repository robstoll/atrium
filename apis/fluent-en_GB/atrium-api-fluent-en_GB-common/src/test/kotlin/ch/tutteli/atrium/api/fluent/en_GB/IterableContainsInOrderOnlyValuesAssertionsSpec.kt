package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

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

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            plant: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.contains.inOrder.only.value(a)
            else plant.contains.inOrder.only.values(a, *aX)

        fun getContainsNullablePair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            plant: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) plant.contains.inOrder.only.value(a)
            else plant.contains.inOrder.only.values(a, *aX)

        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactly

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(
            plant: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.containsExactly(a)
            else plant.containsExactly(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Expect<Iterable<Double?>>, Double?, Array<out Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactly

        fun getContainsNullableShortcutPair() =
            containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(
            plant: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) plant.containsExactly(a)
            else plant.containsExactly(a, *aX)
    }
}

