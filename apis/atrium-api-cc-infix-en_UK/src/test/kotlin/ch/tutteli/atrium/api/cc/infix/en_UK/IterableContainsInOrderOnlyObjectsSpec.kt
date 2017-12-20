package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyObjectsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inOrder $butOnly $inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only `object` a
            } else {
                plant to contain inGiven order but only the Objects(a, *aX)
            }
        }


        private fun getContainsShortcutName(): String {
            val f: KFunction2<AssertionPlant<Iterable<Double>>, Objects<Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::containsStrictly
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant containsStrictly a
            } else {
                plant containsStrictly Objects(a, *aX)
            }
        }
    }

    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )
}

