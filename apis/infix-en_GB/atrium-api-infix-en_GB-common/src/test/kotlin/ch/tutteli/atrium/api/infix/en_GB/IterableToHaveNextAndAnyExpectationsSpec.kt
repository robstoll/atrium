package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableAnyExpectationsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        fun1(Expect<Iterable<Double>>::toHaveNextAndAny),
        fun1(Expect<Iterable<Double?>>::toHaveNextAndAny).withNullableSuffix(),
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair().withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair().withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        getToContainSequencePair(),
        getToContainNullableSequencePair().withNullableSuffix(),
        "[Atrium][Sequence] "
    )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 entry" to Companion::toContainInAnyOrderEntry

        private fun toContainInAnyOrderEntry(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect toContain o inAny order atLeast 1 entry a

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 entry" to Companion::toContainNullableEntry

        private fun toContainNullableEntry(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect toContain o inAny order atLeast 1 entry a

        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContain

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInAnyOrderEntryShortcut

        private fun toContainInAnyOrderEntryShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect toContain a

        private val toContainShortcutNullableFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContain

        fun getToContainNullableShortcutPair() =
            toContainShortcutNullableFun.name to Companion::toContainNullableEntryShortcut

        private fun toContainNullableEntryShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect toContain a


        private fun getToContainSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}() ${toContainShortcutFun.name}" to Companion::toContainInAnyOrderEntrySequence

        private fun toContainInAnyOrderEntrySequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect._logic.changeSubject.unreported { it.asSequence() } asIterable o toContain a

        fun getToContainNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}() ${toContainShortcutNullableFun.name}" to Companion::toContainNullableEntrySequence

        private fun toContainNullableEntrySequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect._logic.changeSubject.unreported { it.asSequence() } asIterable { it toContain a } asIterable o
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1 toHaveNextAndAny {}
        a1 = a1 toContain {}

        a1b = a1b toHaveNextAndAny {}
        a1b = a1b toHaveNextAndAny null
        a1b = a1b toContain {}
        a1b = a1b toContain (null as Double?)

        star = star toHaveNextAndAny {}
        star = star toContain {}
    }
}
