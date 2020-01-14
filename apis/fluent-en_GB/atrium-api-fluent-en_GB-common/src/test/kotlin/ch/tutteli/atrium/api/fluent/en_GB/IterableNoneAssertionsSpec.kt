package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        fun1(Expect<Iterable<Double>>::none),
        fun1(Expect<Iterable<Double?>>::none),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.containsNot.entry(a)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.containsNot.entry(a)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Double>> = notImplemented()
        var a1b: Expect<Iterable<Double?>> = notImplemented()

        var star: Expect<Iterable<*>> = notImplemented()

        a1 = a1.none {}
        a1 = a1.containsNot.entry {}

        a1b = a1b.none {}
        a1b = a1b.none(null)
        a1b = a1b.containsNot.entry {}
        a1b = a1b.containsNot.entry(null)

        star = star.none {}
        star = star.containsNot.entry {}
    }
}
