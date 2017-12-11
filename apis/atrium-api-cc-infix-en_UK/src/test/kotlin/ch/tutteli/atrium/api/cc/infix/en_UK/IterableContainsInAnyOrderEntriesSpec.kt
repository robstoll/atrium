package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderEntriesSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    companion object : IterableContainsSpecBase() {
        fun getEntriesPair()
            = "$the ${Entries::class.simpleName}" to Companion::entries

        private fun entries(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction2<IAssertionPlant<Iterable<Double>>, IAssertionPlant<Double>.() -> Unit, IAssertionPlant<Iterable<Double>>> = IAssertionPlant<Iterable<Double>>::contains
            return f.name
        }

        fun getEntriesShortcutPair()
            = getContainsShortcutName() to Companion::entriesShortcut

        private fun entriesShortcut(plant: IAssertionPlant<Iterable<Double>>, a: IAssertionPlant<Double>.() -> Unit, aX: Array<out IAssertionPlant<Double>.() -> Unit>): IAssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }
    }

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
}
