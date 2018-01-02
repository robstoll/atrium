package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsNullSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    getContainsInAnyOrderNullableEntriesPair(),
    getContainsInAnyOrderOnlyNullableEntriesPair(),
    getContainsInOrderOnlyNullableEntriesPair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ "
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.enthaelt(a, *aX)

        private val containsNotFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::enthaeltNicht
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

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

        fun getContainsInAnyOrderOnlyNullableEntriesPair()
            = "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntriesPair

        private fun containsInAnyOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintraege(a, *aX)
            }
        }

        fun getContainsInOrderOnlyNullableEntriesPair()
            = "$contains.$inAnyOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inGegebenerReihenfolge.nur.eintraege(a, *aX)
            }
        }
    }
}
