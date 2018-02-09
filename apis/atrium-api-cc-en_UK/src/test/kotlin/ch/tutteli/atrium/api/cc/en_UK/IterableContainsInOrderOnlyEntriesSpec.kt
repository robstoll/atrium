package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyEntriesSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesSpec(
        AssertionVerbFactory,
        getContainsPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.entry(a)
            } else {
                plant.contains.inOrder.only.entries(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsStrictly
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.containsStrictly(a, *aX)
    }
}
