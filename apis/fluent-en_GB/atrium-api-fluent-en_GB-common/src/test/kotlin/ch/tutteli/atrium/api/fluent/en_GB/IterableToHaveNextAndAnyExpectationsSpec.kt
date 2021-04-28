package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableToHaveNextAndAnyExpectationsSpec.Companion as C

class IterableToHaveNextAndAnyExpectationsSpec : Spek({
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
        functionDescription to C::toContainEntry,
        (functionDescription to C::toContainNullableEntry).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    // TODO 0.19.0 #722 this will differ once we don't implement the same behaviour for toContain and none
    // that's fine and we can simply remove this test here
    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        shortcutDescription to C::toContainEntryShortcut,
        (shortcutDescription to C::toContainNullableEntryShortcut).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    // TODO move to own SequenceSpec if we really need this (maybe we can also just delete it?)
    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAnyExpectationsSpec(
        getToContainSequencePair(),
        getToContainNullableSequencePair().withNullableSuffix(),
        "[Atrium][Sequence] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$atLeast(1).$entry"
        val shortcutDescription = toContain

        private fun toContainEntry(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.toContain.inAnyOrder.atLeast(1).entry(a)

        private fun toContainNullableEntry(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.toContain.inAnyOrder.atLeast(1).entry(a)


        private fun toContainEntryShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.toContain(a)


        private fun toContainNullableEntryShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect.toContain(a)


        //TODO move to an own spec
        private fun getToContainSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$toContain" to Companion::toContainInAnyOrderEntrySequence

        private fun toContainInAnyOrderEntrySequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect._logic.changeSubject.unreported { it.asSequence() }.asIterable().toContain(a)

        fun getToContainNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$toContain" to Companion::toContainNullableEntrySequence

        private fun toContainNullableEntrySequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect._logic.changeSubject.unreported { it.asSequence() }.asIterable { toContain(a) }.asIterable()
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toHaveNextAndAny {}
        nList = nList.toHaveNextAndAny {}
        subList = subList.toHaveNextAndAny {}
        star = star.toHaveNextAndAny {}
    }
}
