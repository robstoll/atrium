package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.fun1
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        fun1(Expect<Iterable<Double>>::any),
        fun1(Expect<Iterable<Double?>>::any),
        "◆ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsSequencePair(),
        getContainsNullableSequencePair(),
        "◆ ",
        "[Atrium][Sequence] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            plant.contains.inAnyOrder.atLeast(1).entry(a)

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            plant.contains.inAnyOrder.atLeast(1).entry(a)

        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            plant.contains(a)

        private val containsShortcutNullableFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() =
            containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            plant.contains(a)


        private fun getContainsSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntriesSequence

        private fun containsInAnyOrderEntriesSequence(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            ExpectImpl.changeSubject(plant).unreported { it.asSequence() }.asIterable().contains(a)

        fun getContainsNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntriesSequence

        private fun containsNullableEntriesSequence(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            ExpectImpl.changeSubject(plant).unreported { it.asSequence() }.asIterable().contains(a)
    }
}
