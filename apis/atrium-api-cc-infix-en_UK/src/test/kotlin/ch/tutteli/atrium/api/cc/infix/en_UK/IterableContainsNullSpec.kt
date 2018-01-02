package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

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
        //TODO replace with getContainsStrictEntries(),
        getContainsInOrderOnlyNullableEntriesPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        private val containsInAnyOrderNullableValuesFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsInAnyOrderNullableValuesPair() = containsInAnyOrderNullableValuesFun.name to Companion::containsInAnyOrderNullableValues

        private fun containsInAnyOrderNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 value a
            } else {
                plant to contain inAny order atLeast 1 the Values(a, *aX)
            }
        }

        private val containsFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsValuesPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, *aX)
            }
        }

        private val containsNotNullableValuesFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        fun getContainsNotNullableValuesPair() = containsNotNullableValuesFun.name to Companion::containsNotNullableValues

        private fun containsNotNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private val containsNotFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        fun getContainsNotValuesPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }

        fun getContainsInAnyOrderNullableEntriesPair()
            = "$toContain $inAnyOrder $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction2<AssertionPlant<Iterable<Double?>>, Entries<Double, (AssertionPlant<Double>.() -> Unit)?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsEntriesPair() = containsEntriesFun.name to Companion::containsEntries

        private fun containsEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }

        fun getContainsInAnyOrderOnlyNullableEntriesPair()
            = "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntriesPair

        private fun containsInAnyOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order but only entry a
            } else {
                plant to contain inAny order but only the Entries(a, *aX)
            }
        }

        fun getContainsInOrderOnlyNullableEntriesPair()
            = "$toContain $inOrder $butOnly $inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inGiven order but only entry a
            } else {
                plant to contain inGiven order but only the Entries(a, *aX)
            }
        }
    }
}
