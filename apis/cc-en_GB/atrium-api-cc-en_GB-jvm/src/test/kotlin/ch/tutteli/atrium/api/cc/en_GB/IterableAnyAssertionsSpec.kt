package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        Assert<Iterable<Double>>::any.name to Assert<Iterable<Double>>::any,
        Assert<Iterable<Double?>>::any.name to Assert<Iterable<Double?>>::any,
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

    object SequenceSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsSequencePair(),
        getContainsNullableSequencePair(),
        "◆ ",
        "[Atrium][Sequence] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.contains.inAnyOrder.atLeast(1).entry(a)

        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.contains.inAnyOrder.atLeast(1).nullableEntry(a)


        @Suppress("unused")
        private fun checkShortcutOverloadAmbiguity() {
            assert(listOf(1, 2)).contains { toBe(1) }
            assert(listOf(1, 2)).contains({ toBe(1) }, { toBe(2) })
            assert(listOf(null, 1, 2)).contains(null)
            assert(listOf(null, 1, 2)).contains { toBe(1) }
            assert(listOf(null, 1, 2)).contains({ toBe(1) }, null, { toBe(2) })
        }
        
        private val containsShortcutFun : KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.contains(a)

        private val containsShortcutNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::containsNullableEntry
        fun getContainsNullableShortcutPair() = containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.contains(a)


        private fun getContainsSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntriesSequence

        private fun containsInAnyOrderEntriesSequence(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = AssertImpl.changeSubject(plant) { plant.subject.asSequence() }.asIterable().contains(a)

        fun getContainsNullableSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntriesSequence

        private fun containsNullableEntriesSequence(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            =  AssertImpl.changeSubject(plant) { plant.subject.asSequence() }.asIterable().containsNullableEntry(a)
    }
}
