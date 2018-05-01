package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsNullSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsNullSpec(
        AssertionVerbFactory,
        getContainsInAnyOrderNullableValuesPair(),
        getContainsNotNullableValuesPair(),
        getContainsInAnyOrderNullableEntriesPair(),
        getContainsInAnyOrderOnlyNullableEntriesPair(),
        getContainsInOrderOnlyNullableEntriesPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsNullSpec(
        AssertionVerbFactory,
        getContainsValuesPair(),
        getContainsNotValuesPair(),
        getContainsEntriesPair(),
        getContainsInAnyOrderOnlyNullableEntriesPair(),
        getContainsStrictlyEntriesPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        private val containsInAnyOrderNullableValuesFun: KFunction2<Assert<Iterable<Double?>>, Values<Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsInAnyOrderNullableValuesPair() = containsInAnyOrderNullableValuesFun.name to Companion::containsInAnyOrderNullableValues

        private fun containsInAnyOrderNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 value a
            } else {
                plant to contain inAny order atLeast 1 the Values(a, *aX)
            }
        }

        private val containsFun: KFunction2<Assert<Iterable<Double?>>, Values<Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsValuesPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, *aX)
            }
        }


        private val containsNotNullableValuesFun: KFunction2<Assert<Iterable<Double?>>, Values<Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsNot
        fun getContainsNotNullableValuesPair() = containsNotNullableValuesFun.name to Companion::containsNotNullableValues

        private fun containsNotNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private val containsNotFun: KFunction2<Assert<Iterable<Double?>>, Values<Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsNot
        fun getContainsNotValuesPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }


        fun getContainsInAnyOrderNullableEntriesPair()
            = "$toContain $inAnyOrder $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction2<Assert<Iterable<Double?>>, Entries<Double, (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsEntriesPair() = containsEntriesFun.name to Companion::containsEntries

        private fun containsEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }


        fun getContainsInAnyOrderOnlyNullableEntriesPair()
            = "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntriesPair

        private fun containsInAnyOrderOnlyNullableEntriesPair(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the Entries(a, *aX)
            }
        }


        private val containsStrictlyEntriesFun: KFunction2<Assert<Iterable<Double?>>, Entries<Double, (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsStrictly
        fun getContainsStrictlyEntriesPair() = containsStrictlyEntriesFun.name to Companion::containsStrictlyEntries

        private fun containsStrictlyEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant containsStrictly a
            } else {
                plant containsStrictly Entries(a, *aX)
            }
        }

        fun getContainsInOrderOnlyNullableEntriesPair()
            = "$toContain $inOrder $butOnly $inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only entry a
            } else {
                plant to contain inGiven order but only the Entries(a, *aX)
            }
        }
    }
}
