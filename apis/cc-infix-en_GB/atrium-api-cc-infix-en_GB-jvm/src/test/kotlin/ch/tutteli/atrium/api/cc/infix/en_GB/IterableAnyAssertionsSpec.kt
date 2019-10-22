@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableAnyAssertionsSpec(
        AssertionVerbFactory,
        getAnyPair(),
        getAnyNullablePair(),
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
        private val anyFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::any
        fun getAnyPair() = anyFun.name to Companion::any

        private fun any(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant any a

        private val anyNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::any
        fun getAnyNullablePair() = anyNullableFun.name to Companion::anyNullable

        private fun anyNullable(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant any a


        fun getContainsPair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant to contain inAny order atLeast 1 entry a

        fun getContainsNullablePair()
            = "$toContain $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant to contain inAny order atLeast 1 entry a


        private val containsShortcutFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant contains a

        private val containsShortcutNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::contains
        fun getContainsNullableShortcutPair() = containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant contains a


        private fun getContainsSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntriesSequence

        @Suppress("DEPRECATION")
        private fun containsInAnyOrderEntriesSequence(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = ExpectImpl.changeSubject(plant).unreported { it.asSequence() }.asIterable() contains a

        fun getContainsNullableSequencePair()
            = "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntriesSequence

        @Suppress("DEPRECATION")
        private fun containsNullableEntriesSequence(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            =  ExpectImpl.changeSubject(plant).unreported { it.asSequence() }.asIterable() contains a
    }
}
