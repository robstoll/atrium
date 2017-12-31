package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
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

    companion object {
        fun getEntriesPair()
            = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderSearchBehaviour>::eintraege.name to Companion::entries

        private fun entries(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction3<AssertionPlant<Iterable<Double>>, AssertionPlant<Double>.() -> Unit, Array<out AssertionPlant<Double>.() -> Unit>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaelt
            return f.name
        }

        fun getEntriesShortcutPair()
            = getContainsShortcutName() to Companion::entriesShortcut

        private fun entriesShortcut(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>)
            = plant.enthaelt(a, *aX)
    }
}
