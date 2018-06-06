package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        Assert<Iterable<Double>>::any.name to Assert<Iterable<Double>>::any,
        Assert<Iterable<Double?>>::anyOfNullable.name to Assert<Iterable<Double?>>::anyOfNullable,
        "◆ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.contains.inAnyOrder.atLeast(1).entry(a)

        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries nullable" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.contains.inAnyOrder.atLeast(1).nullableEntry(a)

        private val containsShortcutFun : KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.contains(a)

        private val containsShortcutNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsNullableEntry
        fun getContainsNullableShortcutPair() = containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.containsNullableEntry(a)
    }
}
