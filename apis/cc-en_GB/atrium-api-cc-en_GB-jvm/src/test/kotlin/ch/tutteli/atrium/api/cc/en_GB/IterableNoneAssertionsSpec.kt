@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        Assert<Iterable<Double>>::none.name to Assert<Iterable<Double>>::none,
        Assert<Iterable<Double?>>::none.name to Assert<Iterable<Double?>>::none,
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair()
            = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
                = plant.containsNot.entry(a)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
                = plant.containsNot.entry(a)
    }
}
