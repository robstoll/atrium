package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsNotValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "* ", "(/) ", "(x) ", "- ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotShortcutPair(),
        getContainsNotNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "- ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(
            plant: Assert<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaeltNicht.wert(a)
            } else {
                plant.enthaeltNicht.werte(a, *aX)
            }
        }

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(
            plant: Assert<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaeltNicht.nullableWert(a)
            } else {
                plant.enthaeltNicht.nullableWerte(a, *aX)
            }
        }


        private val containsNotShortcutFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> =
            Assert<Iterable<Double>>::enthaeltNicht

        private fun getContainsNotShortcutPair() = containsNotShortcutFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>) =
            plant.enthaeltNicht(a, *aX)

        private val containsNotNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, Double?, Array<out Double?>, Assert<Iterable<Double?>>> =
            @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
            Assert<Iterable<Double?>>::enthaeltNicht

        private fun getContainsNotNullableShortcutPair() =
            containsNotNullableShortcutFun.name to Companion::containsNotNullableShortcut

        private fun containsNotNullableShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>) =
            @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */) plant.enthaeltNicht(a, *aX)
    }
}
