package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.specs.include
import ch.tutteli.atrium.specs.fun1

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        fun1(Expect<Iterable<Double>>::none),
        fun1(Expect<Iterable<Double?>>::none),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair()
            = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit)
                = plant.containsNot.entry(a)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?)
                = plant.containsNot.entry(a)
    }
}
