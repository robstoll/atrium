package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyObjectsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInOrderOnlyObjectsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.objekt(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.objekte(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaeltStrikt
            return f.name
        }

        fun getContainsShortcutPair()
            = getContainsShortcutName() to Companion::containsInOrderOnlyShortcut

        private fun containsInOrderOnlyShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaeltStrikt(a, *aX)
    }
}

