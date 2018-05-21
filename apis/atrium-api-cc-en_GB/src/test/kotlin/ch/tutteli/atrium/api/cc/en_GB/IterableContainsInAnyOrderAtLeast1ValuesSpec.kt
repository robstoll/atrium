package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderAtLeast1ValuesSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1ValuesSpec(
        AssertionVerbFactory,
        getContainsNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1ValuesSpec(
        AssertionVerbFactory,
        getContainsNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderValues nullable" to Companion::containsNullableValues

        private fun containsNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).nullableValue(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).nullableValues(a, *aX)
            }
        }

        private val containsNullableFun: KFunction3<Assert<Iterable<Double?>>, Double, Array<out Double?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsNullable
        fun getContainsNullableShortcutPair() = containsNullableFun.name + " nullable" to Companion::containsNullableValuesShortcut

        private fun containsNullableValuesShortcut(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.containsNullable(a, *aX)
    }
}
