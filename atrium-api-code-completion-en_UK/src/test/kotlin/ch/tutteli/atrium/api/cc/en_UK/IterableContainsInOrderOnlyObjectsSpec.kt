package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
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

        private fun containsInOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.contains.inOrder.only.values(a, *aX)

        private fun getContainsShortcutName(): String {
            val f: KFunction3<IAssertionPlant<Iterable<Double>>, Double, Array<out Double>, IAssertionPlant<Iterable<Double>>> = IAssertionPlant<Iterable<Double>>::containsStrictly
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: IAssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.containsStrictly(a, *aX)
    }

    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
    )
}

