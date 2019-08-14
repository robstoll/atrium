package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(
            plant: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(
            plant: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }


        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(
            plant: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains(a)
            } else {
                plant.contains(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction3<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() = containsEntriesFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(
            plant: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains(a)
            } else {
                plant.contains(a, *aX)
            }
        }
    }
}
