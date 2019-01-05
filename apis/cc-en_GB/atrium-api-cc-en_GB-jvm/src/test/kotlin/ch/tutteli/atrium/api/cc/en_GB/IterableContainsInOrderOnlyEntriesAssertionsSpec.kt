package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(OldShortcutSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object OldShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        oldGetContainsShortcutPair(),
        oldGetContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.entry(a)
            } else {
                plant.contains.inOrder.only.entries(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries nullable" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.nullableEntry(a)
            } else {
                plant.contains.inOrder.only.nullableEntries(a, *aX)
            }
        }

        // Tests with old "containsStrictly", remove after deprecated methods are removed
        private val oldContainsShortcutFun: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsStrictly
        fun oldGetContainsShortcutPair() = oldContainsShortcutFun.name to Companion::oldContainsInOrderOnlyEntriesShortcut

        private fun oldContainsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.containsStrictly(a, *aX)

        private val oldContainsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsStrictlyNullableEntries
        fun oldGetContainsNullableShortcutPair() = oldContainsNullableShortcutFun.name to Companion::oldContainsInOrderOnlyNullableEntriesShortcut

        private fun oldContainsInOrderOnlyNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsStrictlyNullableEntry(a)
            } else {
                plant.containsStrictlyNullableEntries(a, *aX)
            }
        }

        // Tests with "containsExactly"
        private val containsShortcutFun: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsExactly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.containsExactly(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsExactlyNullableEntries
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableEntriesShortcut

        private fun containsInOrderOnlyNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsExactlyNullableEntry(a)
            } else {
                plant.containsExactlyNullableEntries(a, *aX)
            }
        }
    }
}
