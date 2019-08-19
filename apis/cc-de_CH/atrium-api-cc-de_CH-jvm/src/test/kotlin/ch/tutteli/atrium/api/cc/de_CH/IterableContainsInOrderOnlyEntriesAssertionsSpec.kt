@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.esGilt
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyEntries

        private fun containsInOrderOnlyEntries(
            plant: Assert<Iterable<Double>>,
            a: Assert<Double>.() -> Unit,
            aX: Array<out Assert<Double>.() -> Unit>
        ): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintraege(a, *aX)
            }
        }

        fun getContainsNullablePair() =
            "$contains.$inOrder.$only.$inOrderOnlyEntries nullable" to Companion::containsInOrderOnlyNullableEntries

        private fun containsInOrderOnlyNullableEntries(
            plant: Assert<Iterable<Double?>>,
            a: (Assert<Double>.() -> Unit)?,
            aX: Array<out (Assert<Double>.() -> Unit)?>
        ): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintraege(a, *aX)
            }
        }

        private val containsShortcutFun: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> =
            Assert<Iterable<Double>>::enthaeltExakt

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaeltExakt { a() }
            } else {
                plant.enthaeltExakt(a, *aX)
            }
        }

        private val containsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaeltExakt
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableEntriesShortcut

        private fun containsInOrderOnlyNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                if (a == null) plant.enthaeltExakt(a)
                else plant.enthaeltExakt { a() }
            } else {
                plant.enthaeltExakt(a, *aX)
            }
        }
    }
}

