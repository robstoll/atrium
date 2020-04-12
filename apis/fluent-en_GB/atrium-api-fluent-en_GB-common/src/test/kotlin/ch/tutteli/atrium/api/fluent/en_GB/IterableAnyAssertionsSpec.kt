package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableAnyAssertionsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        fun1(Expect<Iterable<Double>>::any),
        fun1(Expect<Iterable<Double?>>::any).withNullableSuffix(),
        "◆ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair().withNullableSuffix(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair().withNullableSuffix(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsSequencePair(),
        getContainsNullableSequencePair().withNullableSuffix(),
        "◆ ",
        "[Atrium][Sequence] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsInAnyOrderEntry

        private fun containsInAnyOrderEntry(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntry

        private fun containsNullableEntry(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)

        private val containsShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntryShortcut

        private fun containsInAnyOrderEntryShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains(a)

        private val containsShortcutNullableFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() =
            containsShortcutNullableFun.name to Companion::containsNullableEntryShortcut

        private fun containsNullableEntryShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect.contains(a)


        private fun getContainsSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntrySequence

        private fun containsInAnyOrderEntrySequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            ExpectImpl.changeSubject(expect).unreported { it.asSequence() }.asIterable().contains(a)

        fun getContainsNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntrySequence

        private fun containsNullableEntrySequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = ExpectImpl.changeSubject(expect).unreported { it.asSequence() }.asIterable().contains(a)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.any {}
        a1 = a1.contains {}

        a1b = a1b.any {}
        a1b = a1b.any(null)
        a1b = a1b.contains {}
        a1b = a1b.contains(null)

        star = star.any {}
        star = star.contains {}
    }
}
