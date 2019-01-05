package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(OldShortcutSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object OldShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        oldGetContainsShortcutPair(),
        oldGetContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyEntries

        private fun containsInOrderOnlyEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintraege(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries nullable" to Companion::containsInOrderOnlyNullableEntries

        private fun containsInOrderOnlyNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.nullableEintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.nullableEintraege(a, *aX)
            }
        }

        // Tests with old "enthaeltStrikt", remove after deprecated methods are removed
        private val oldContainsShortcutFun: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaeltStrikt
        fun oldGetContainsShortcutPair() = oldContainsShortcutFun.name to Companion::oldContainsInOrderOnlyEntriesShortcut

        private fun oldContainsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.enthaeltStrikt(a, *aX)

        private val oldContainsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaeltStriktNullableEintraege
        fun oldGetContainsNullableShortcutPair() = oldContainsNullableShortcutFun.name to Companion::oldContainsInOrderOnlyNullableEntriesShortcut

        private fun oldContainsInOrderOnlyNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaeltStriktNullableEintrag(a)
            } else {
                plant.enthaeltStriktNullableEintraege(a, *aX)
            }
        }

        // Tests with "enthaeltExakt"
        private val containsShortcutFun: KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaeltExakt
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>)
            = plant.enthaeltExakt(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaeltExaktNullableEintraege
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableEntriesShortcut

        private fun containsInOrderOnlyNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaeltExaktNullableEintrag(a)
            } else {
                plant.enthaeltExaktNullableEintraege(a, *aX)
            }
        }
    }
}

