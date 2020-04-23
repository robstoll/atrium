// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsNotValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotShortcutPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private val containsNotShortcutFun : KFunction2<Assert<Iterable<Double>>, Double, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
        private fun getContainsNotShortcutPair() = containsNotShortcutFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }
    }
}
