package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

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
        private val containsInAnyOrderNullableValuesFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsInAnyOrderNullableValuesPair() = containsInAnyOrderNullableValuesFun.name to Companion::containsInAnyOrderNullableValues

        private fun containsInAnyOrderNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).value(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).values(a, *aX)
            }
        }

        private val containsFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsValuesPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.contains(a, *aX)


        private val containsNotNullableValuesFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        fun getContainsNotNullableValuesPair() = containsNotNullableValuesFun.name to Companion::containsNotNullableValues

        private fun containsNotNullableValues(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsNot.value(a)
            } else {
                plant.containsNot.values(a, *aX)
            }
        }

        private val containsNotFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        private fun getContainsNotValuesPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.containsNot(a, *aX)


        fun getContainsInAnyOrderNullableEntriesPair()
            = "$contains.$inAnyOrder.$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction3<AssertionPlant<Iterable<Double?>>, (AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsEntriesPair() = containsEntriesFun.name to Companion::containsEntries

        private fun containsEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>)
            = plant.contains(a, *aX)


        fun getContainsInAnyOrderOnlyNullableEntriesPair()
            = "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnlyNullableEntriesPair

        private fun containsInAnyOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.only.entry(a)
            } else {
                plant.contains.inAnyOrder.only.entries(a, *aX)
            }
        }

        fun getContainsInOrderOnlyNullableEntriesPair()
            = "$contains.$inOrder.$only.$inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.entry(a)
            } else {
                plant.contains.inOrder.only.entries(a, *aX)
            }
        }
    }
}
