package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.esGilt
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
        Assert<Iterable<Double>>::irgendEiner.name to Assert<Iterable<Double>>::irgendEiner,
        Assert<Iterable<Double?>>::irgendEiner.name to Assert<Iterable<Double?>>::irgendEiner,
        "* ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "* ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ",
        "[Atrium][Shortcut] "
    )

    object SequenceSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getContainsSequencePair(),
        getContainsNullableSequencePair(),
        "* ",
        "[Atrium][Sequence] "
    )


    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(a)

        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(a)


        @Suppress("unused")
        private fun checkShortcutOverloadAmbiguity() {
            esGilt(listOf(1, 2)).enthaelt { ist(1) }
            esGilt(listOf(1, 2)).enthaelt({ ist(1) }, { ist(2) })
            esGilt(listOf(null, 1, 2)).enthaelt(null)
            esGilt(listOf(null, 1, 2)).enthaelt { ist(1) }
            esGilt(listOf(null, 1, 2)).enthaelt({ ist(1) }, null, { ist(2) })
        }

        private val containsShortcutFun : KFunction3<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaelt
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.enthaelt(a)

        private val containsShortcutNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::enthaelt
        fun getContainsNullableShortcutPair() = containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.enthaelt(a)


        private fun getContainsSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntriesSequence

        private fun containsInAnyOrderEntriesSequence(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = AssertImpl.changeSubject(plant) { plant.subject.asSequence() }.asIterable().enthaelt(a)

        fun getContainsNullableSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntriesSequence

        private fun containsNullableEntriesSequence(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            =  AssertImpl.changeSubject(plant) { plant.subject.asSequence() }.asIterable().enthaelt(a)
    }
}
