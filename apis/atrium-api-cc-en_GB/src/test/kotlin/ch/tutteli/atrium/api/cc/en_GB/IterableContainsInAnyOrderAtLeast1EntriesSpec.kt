package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderAtLeast1EntriesSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1EntriesSpec(
        AssertionVerbFactory,
        getContainsNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1EntriesSpec(
        AssertionVerbFactory,
        getContainsNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries nullable" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction3<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsNullableShortcutPair() = containsEntriesFun.name + " nullable" to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>)
            = plant.contains(a, *aX)
    }
}
