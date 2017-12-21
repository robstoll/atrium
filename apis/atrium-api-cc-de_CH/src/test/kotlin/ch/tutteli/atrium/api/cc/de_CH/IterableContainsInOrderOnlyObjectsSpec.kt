package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyObjectsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.objekt(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.objekte(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaeltStrikt
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaeltStrikt(a, *aX)
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

