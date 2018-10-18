@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inOrder $butOnly $inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only value a
            } else {
                plant to contain inGiven order but only the Values(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$toContain $inOrder $butOnly $inOrderOnlyValues nullable" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only value a
            } else {
                plant to contain inGiven order but only the Values(a, *aX)
            }
        }

        private val containsShortcutFun: KFunction2<Assert<Iterable<Double>>, Values<Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsStrictly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return when {
                aX.isEmpty() -> plant containsStrictly a
                aX.size == 1 -> plant containsStrictly Values(a, *aX)
                else -> plant containsStrictly Values(a, *aX)
            }
        }

        private val containsNullableShortcutFun: KFunction2<Assert<Iterable<Double?>>, Values<Double>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsStrictly
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name + " nullable" to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.containsStrictly(Values(a, *aX))
    }
}
