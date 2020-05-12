// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.migration.asSubExpect
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inOrder $butOnly $inOrderOnlyEntries" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                (plant.asExpect().contains(o) inGiven order and only).entry(asSubExpect(a)).asAssert()
            } else {
                (plant.asExpect().contains(o) inGiven order and only).the(Entries(a, *aX).mapArguments.to { asSubExpect(it) }
                    .let { (first, rest) -> entries(first, *rest) }).asAssert()
            }
        }

        fun getContainsNullablePair()
            = "$toContain $inOrder $butOnly $inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                (plant.asExpect().contains(o) inGiven order and only).entry(asSubExpect(a)).asAssert()
            } else {
                (plant.asExpect().contains(o) inGiven order and only).the(Entries(a, *aX).mapArguments.to {
                    asSubExpect(it)
                }.let { (first, rest) -> entries(first, *rest) }).asAssert()
            }
        }

        private val containsShortcutFun: KFunction2<Assert<Iterable<Double>>, Entries<Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsExactly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsExactly(asSubExpect(a)).asAssert()
            } else {
                plant.asExpect().containsExactly(Entries(a, *aX).mapArguments.to { asSubExpect(it) }
                    .let { (first, rest) -> entries(first, *rest) }).asAssert()
            }
        }

        private val containsNullableShortcutFun: KFunction2<Assert<Iterable<Double?>>, Entries<Double>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsExactly
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsStrictlyEntries

        private fun containsStrictlyEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsExactly(asSubExpect(a)).asAssert()
            } else {
                plant.asExpect().containsExactly(Entries(a, *aX).mapArguments.to { asSubExpect(it) }
                    .let { (first, rest) -> entries(first, *rest) }).asAssert()
            }
        }
    }
}
