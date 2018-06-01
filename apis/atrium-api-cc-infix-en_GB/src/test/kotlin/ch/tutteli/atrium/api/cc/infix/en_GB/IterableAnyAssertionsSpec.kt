package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getAnyPair(),
        getAnyNullablePair(),
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        private val anyFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::any
        fun getAnyPair() = anyFun.name to Companion::any

        private fun any(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant any a

        private val anyNullableFun: KFunction2<Assert<Iterable<Double?>>, NullableEntry<Double>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::any
        fun getAnyNullablePair() = anyNullableFun.name to Companion::anyNullable

        private fun anyNullable(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant any NullableEntry(a)


        fun getContainsPair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant to contain inAny order atLeast 1 entry a

        fun getContainsNullablePair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries nullable" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant to contain inAny order atLeast 1 nullableEntry a


        private val containsShortcutFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant contains a

        private val containsShortcutNullableFun: KFunction2<Assert<Iterable<Double?>>, NullableEntry<Double>, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsNullableShortcutPair() = containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant contains NullableEntry(a)
    }
}
