package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "* ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderValues" to Companion::containsValues

        private fun containsValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderValues" to Companion::containsNullableValues

        private fun containsNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(a, *aX)
            }
        }


        private val containsFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaelt
        fun getContainsShortcutPair() = containsFun.name to Companion::containsValuesShortcut

        private fun containsValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt(a)
            } else {
                plant.enthaelt(a, *aX)
            }
        }

        private val containsNullableFun: KFunction3<Assert<Iterable<Double?>>, Double?, Array<out Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaelt
        fun getContainsNullableShortcutPair() = containsNullableFun.name to Companion::containsNullableValuesShortcut

        private fun containsNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt(a)
            } else {
                plant.enthaelt(a, *aX)
            }
        }
    }
}
