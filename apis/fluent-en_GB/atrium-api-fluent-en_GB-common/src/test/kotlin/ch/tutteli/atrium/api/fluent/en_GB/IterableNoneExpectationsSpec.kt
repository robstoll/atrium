package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableNoneExpectationsSpec.Companion as C

class IterableNoneExpectationsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableNoneExpectationsSpec(
        fun1(Expect<Iterable<Double>>::none),
        fun1(Expect<Iterable<Double?>>::none).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Predicate] "
    )

    // TODO 0.17.0 #722 this will differ once we don't implement the same behaviour for contains and none
    // that's fine and we can simply remove this test here
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableNoneExpectationsSpec(
        functionDescription to C::containsNotFun,
        (functionDescription to C::containsNotNullableFun).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$containsNot.$entry"

        private fun containsNotFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.containsNot.entry(a)

        private fun containsNotNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.containsNot.entry(a)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.none{}
        nList = nList.none{}
        subList = subList.none{}
        star = star.none{}
    }
}
