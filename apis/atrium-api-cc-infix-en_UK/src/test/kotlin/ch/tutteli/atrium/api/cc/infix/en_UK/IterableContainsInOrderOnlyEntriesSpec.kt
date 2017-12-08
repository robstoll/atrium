package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyEntriesSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$toContain $inOrder $butOnly $inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only entry a
            } else {
                plant to contain inGiven order but only the Entries(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction2<IAssertionPlant<Iterable<Double>>, Entries<Double>, IAssertionPlant<Iterable<Double>>> = IAssertionPlant<Iterable<Double>>::containsStrictly
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant containsStrictly a
            } else {
                plant containsStrictly Entries(a, *aX)
            }
        }
    }

    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyEntriesSpec(
        AssertionVerbFactory,
        getContainsPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyEntriesSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
    )
}
