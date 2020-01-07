package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
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

        private fun containsInAnyOrderEntries(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)

        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains(a)

        private val containsShortcutNullableFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() =
            containsShortcutNullableFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect.contains(a)


        private fun getContainsSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutFun.name}" to Companion::containsInAnyOrderEntriesSequence

        private fun containsInAnyOrderEntriesSequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            ExpectImpl.changeSubject(expect).unreported { it.asSequence() }.asIterable().contains(a)

        fun getContainsNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().${containsShortcutNullableFun.name}" to Companion::containsNullableEntriesSequence

        private fun containsNullableEntriesSequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = ExpectImpl.changeSubject(expect).unreported { it.asSequence() }.asIterable().contains(a)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Double>> = notImplemented()
        var a1b: Expect<Iterable<Double?>> = notImplemented()

        var star: Expect<Iterable<*>> = notImplemented()

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
