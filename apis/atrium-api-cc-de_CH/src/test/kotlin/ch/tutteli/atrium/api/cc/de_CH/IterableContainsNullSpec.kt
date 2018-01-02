package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsNullSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
        AssertionVerbFactory,
        getContainsInAnyOrderNullableValuesPair(),
        getContainsNotNullableValuesPair(),
        getContainsInAnyOrderNullableEntriesPair(),
        getContainsInAnyOrderOnlyNullableEntriesPair(),
        getContainsInOrderOnlyNullableEntriesPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
        AssertionVerbFactory,
        getContainsValuesPair(),
        getContainsNotValuesPair(),
        getContainsEntriesPair(),
        getContainsInAnyOrderOnlyNullableEntriesPair(),
        getContainsStrictlyEntriesPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        private val containsInAnyOrderNullableValuesFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaelt
        fun getContainsInAnyOrderNullableValuesPair() = containsInAnyOrderNullableValuesFun.name to Companion::containsInAnyOrderNullableValues

        private fun containsInAnyOrderNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(a)
            }
        }

        private val containsFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaelt
        fun getContainsValuesPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.enthaelt(a, *aX)


        private val containsNotNullableValuesFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaeltNicht
        fun getContainsNotNullableValuesPair() = containsNotNullableValuesFun.name to Companion::containsNotNullableValues

        private fun containsNotNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaeltNicht.wert(a)
            } else {
                plant.enthaeltNicht.werte(a, *aX)
            }
        }

        private val containsNotFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaeltNicht
        private fun getContainsNotValuesPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.enthaeltNicht(a, *aX)


        fun getContainsInAnyOrderNullableEntriesPair()
            = "$contains.$inAnyOrder.$inAnyOrderEntries" to Companion::containsInAnyOrderNullableEntries

        private fun containsInAnyOrderNullableEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction3<AssertionPlant<Iterable<Double?>>, (AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaelt
        fun getContainsEntriesPair() = containsEntriesFun.name to Companion::containsEntries

        private fun containsEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>)
            = plant.enthaelt(a, *aX)


        fun getContainsInAnyOrderOnlyNullableEntriesPair()
            = "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntriesPair

        private fun containsInAnyOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintraege(a, *aX)
            }
        }


        private val containsStrictlyEntriesFun: KFunction3<AssertionPlant<Iterable<Double?>>, (AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaeltStrikt
        fun getContainsStrictlyEntriesPair() = containsStrictlyEntriesFun.name to Companion::containsStrictlyEntries

        private fun containsStrictlyEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>)
            = plant.enthaeltStrikt(a, *aX)

        fun getContainsInOrderOnlyNullableEntriesPair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintraege(a, *aX)
            }
        }
    }
}
