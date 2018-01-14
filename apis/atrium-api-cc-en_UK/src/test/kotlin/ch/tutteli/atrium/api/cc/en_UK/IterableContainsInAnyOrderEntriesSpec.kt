package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderEntriesSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderEntriesSpec(
        AssertionVerbFactory,
        getEntriesPair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderEntriesSpec(
        AssertionVerbFactory,
        getEntriesShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object: IterableContainsSpecBase() {
        fun getEntriesPair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::entries

        private fun entries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
            return f.name
        }

        fun getEntriesShortcutPair()
            = getContainsShortcutName() to Companion::entriesShortcut

        private fun entriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.contains(a, *aX)
    }
}
