package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.specs.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit, aX: Array<out Expect<Double>.() -> Unit>): Expect<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.entry(a)
            } else {
                plant.contains.inOrder.only.entries(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?, aX: Array<out (Expect<Double>.() -> Unit)?>): Expect<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.entry(a)
            } else {
                plant.contains.inOrder.only.entries(a, *aX)
            }
        }

        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, Expect<Iterable<Double>>> = Expect<Iterable<Double>>::containsExactly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit, aX: Array<out Expect<Double>.() -> Unit>): Expect<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.containsExactly { a() }
            } else {
                plant.containsExactly(a, *aX)
            }
        }

        private val containsNullableShortcutFun: KFunction3<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, Expect<Iterable<Double?>>> = Expect<Iterable<Double?>>::containsExactly
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableEntriesShortcut

        private fun containsInOrderOnlyNullableEntriesShortcut(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?, aX: Array<out (Expect<Double>.() -> Unit)?>): Expect<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                if (a == null) plant.containsExactly(a)
                else plant.containsExactly { a() }
            } else {
                plant.containsExactly(a, *aX)
            }
        }
    }
}
