@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.werte(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.werte(a, *aX)
            }
        }

        private val containsShortcutFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaeltExakt
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut
        private fun containsInOrderOnlyValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>) = plant.enthaeltExakt(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, Double?, Array<out Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaeltExakt
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaeltExakt(a)
            } else {
                plant.enthaeltExakt(a, *aX)
            }
        }
    }
}

