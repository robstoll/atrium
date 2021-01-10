package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2
import ch.tutteli.atrium.api.fluent.en_GB.IterableAnyAssertionsSpec.Companion as C

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
        functionDescription to C::containsEntry,
        (functionDescription to C::containsNullableEntry).withNullableSuffix(),
        "◆ ",
        "[Atrium][Builder] "
    )

    // TODO 0.17.0 #722 this will differ once we don't implement the same behaviour for contains and none
    // that's fine and we can simply remove this test here
    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        shortcutDescription to C::containsEntryShortcut,
        (shortcutDescription to C::containsNullableEntryShortcut).withNullableSuffix(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    // TODO move to own SequenceSpec if we really need this (maybe we can also just delete it?)
    object SequenceSpec : ch.tutteli.atrium.specs.integration.IterableAnyAssertionsSpec(
        getContainsSequencePair(),
        getContainsNullableSequencePair().withNullableSuffix(),
        "◆ ",
        "[Atrium][Sequence] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$atLeast(1).$entry"
        val shortcutDescription = "$contains"

        private fun containsEntry(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)

        private fun containsNullableEntry(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.contains.inAnyOrder.atLeast(1).entry(a)


        private fun containsEntryShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.contains(a)


        private fun containsNullableEntryShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect.contains(a)


        //TODO move to an own spec
        private fun getContainsSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$contains" to Companion::containsInAnyOrderEntrySequence

        private fun containsInAnyOrderEntrySequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect._logic.changeSubject.unreported { it.asSequence() }.asIterable().contains(a)

        fun getContainsNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$contains" to Companion::containsNullableEntrySequence

        private fun containsNullableEntrySequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect._logic.changeSubject.unreported { it.asSequence() }.asIterable { contains(a) }.asIterable()
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.any {}
        nList = nList.any {}
        subList = subList.any {}
        star = star.any {}
    }
}
