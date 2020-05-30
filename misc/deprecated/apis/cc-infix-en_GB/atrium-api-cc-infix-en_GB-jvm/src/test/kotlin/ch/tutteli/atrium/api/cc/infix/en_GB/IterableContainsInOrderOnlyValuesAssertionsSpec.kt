// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.cc.infix.en_GB.the
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
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
                ((plant.asExpect().contains(o) inGiven ch.tutteli.atrium.api.infix.en_GB.order).and(ch.tutteli.atrium.api.infix.en_GB.only)).value(a).asAssert()
            } else {
                val values = Values(a, *aX)
                ((plant.asExpect().contains(o) inGiven ch.tutteli.atrium.api.infix.en_GB.order).and(ch.tutteli.atrium.api.infix.en_GB.only)).the<Double, Iterable<Double>>(
                    values(
                        values.expected,
                        *values.otherExpected
                    )
                ).asAssert()
            }
        }

        fun getContainsNullablePair()
            = "$toContain $inOrder $butOnly $inOrderOnlyValues" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                ((plant.asExpect().contains(o) inGiven ch.tutteli.atrium.api.infix.en_GB.order).and(ch.tutteli.atrium.api.infix.en_GB.only)).value(a).asAssert()
            } else {
                val values = Values(a, *aX)
                ((plant.asExpect().contains(o) inGiven ch.tutteli.atrium.api.infix.en_GB.order).and(ch.tutteli.atrium.api.infix.en_GB.only)).the(
                    values(
                        values.expected,
                        *values.otherExpected
                    )
                ).asAssert()
            }
        }

        private val containsShortcutFun: KFunction2<Assert<Iterable<Double>>, Values<Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsExactly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsExactly(a).asAssert()
            } else {
                val values = Values(a, *aX)
                plant.asExpect()
                    .containsExactly(ch.tutteli.atrium.api.infix.en_GB.values(values.expected, *values.otherExpected))
                    .asAssert()
            }
        }

        private val containsNullableShortcutFun: KFunction2<Assert<Iterable<Double?>>, Values<Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsExactly
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name + " $Values" to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsExactly(a).asAssert()
            } else {
                val values = Values(a, *aX)
                plant.asExpect()
                    .containsExactly(ch.tutteli.atrium.api.infix.en_GB.values(values.expected, *values.otherExpected))
                    .asAssert()
            }
        }
    }
}
