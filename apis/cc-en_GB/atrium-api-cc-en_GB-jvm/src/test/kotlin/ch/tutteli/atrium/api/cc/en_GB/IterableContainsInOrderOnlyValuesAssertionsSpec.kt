package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(OldShortcutSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object OldShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        AssertionVerbFactory,
        oldGetContainsShortcutPair(),
        oldGetContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
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
            = "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.value(a)
            } else {
                plant.contains.inOrder.only.values(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$inOrderOnlyValues nullable" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inOrder.only.nullableValue(a)
            } else {
                plant.contains.inOrder.only.nullableValues(a, *aX)
            }
        }

        // Tests with old "containsStrictly", remove after deprecated methods are removed
        private val oldContainsShortcutFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsStrictly
        fun oldGetContainsShortcutPair() = oldContainsShortcutFun.name to Companion::oldContainsInOrderOnlyValuesShortcut

        private fun oldContainsInOrderOnlyValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.containsStrictly(a, *aX)

        private val oldContainsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, Double?, Array<out Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsStrictlyNullableValues
        fun oldGetContainsNullableShortcutPair() = oldContainsNullableShortcutFun.name to Companion::oldContainsInOrderOnlyNullableValuesShortcut

        private fun oldContainsInOrderOnlyNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsStrictlyNullableValue(a)
            } else {
                plant.containsStrictlyNullableValues(a, *aX)
            }
        }

        // Tests with "containsExactly"
        private val containsShortcutFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsExactly
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.containsExactly(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Assert<Iterable<Double?>>, Double?, Array<out Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsExactlyNullableValues
        fun getContainsNullableShortcutPair() = containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsExactlyNullableValue(a)
            } else {
                plant.containsExactlyNullableValues(a, *aX)
            }
        }
    }
}

