package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToHaveElementsAndAnyExpectationsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
    include(ShortcutSpec)
    include(SequenceSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAnyExpectationsSpec(
        fun1(Expect<Iterable<Double>>::toHaveElementsAndAny),
        fun1(Expect<Iterable<Double?>>::toHaveElementsAndAny).withNullableSuffix(),
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAnyExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair().withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAnyExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair().withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAnyExpectationsSpec(
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
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toHaveElementsAndAny {}
        nList = nList toHaveElementsAndAny {}
        subList = subList toHaveElementsAndAny {}
        star = star toHaveElementsAndAny {}

        nList = nList toHaveElementsAndAny null
        subList = subList toHaveElementsAndAny null
        star = star toHaveElementsAndAny null
    }
}
