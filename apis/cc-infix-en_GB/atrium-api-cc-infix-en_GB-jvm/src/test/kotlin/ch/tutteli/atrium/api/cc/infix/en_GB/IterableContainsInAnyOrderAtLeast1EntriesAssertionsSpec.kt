package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }


        private val containsShortcutFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }

        private val containsEntriesFun: KFunction2<Assert<Iterable<Double?>>, Entries<Double>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsNullableShortcutPair() = containsEntriesFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }
    }
}
