// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.api.infix.en_GB.values

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
                plant.asExpect().containsNot(o).value(a).asAssert()
            } else {
                val values = Values(a, *aX)
                plant.asExpect().containsNot(o).the(values(values.expected, *values.otherExpected)).asAssert()
            }
        }

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsNot(o).value(a).asAssert()
            } else {
                val values = Values(a, *aX)
                plant.asExpect().containsNot(o).the(values(values.expected, *values.otherExpected)).asAssert()
            }
        }

        private val containsNotShortcutFun : KFunction2<Assert<Iterable<Double>>, Double, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
        private fun getContainsNotShortcutPair() = containsNotShortcutFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsNot(a).asAssert()
            } else {
                val values = Values(a, *aX)
                plant.asExpect()
                    .containsNot(values(values.expected, *values.otherExpected))
                    .asAssert()
            }
        }
    }
}
