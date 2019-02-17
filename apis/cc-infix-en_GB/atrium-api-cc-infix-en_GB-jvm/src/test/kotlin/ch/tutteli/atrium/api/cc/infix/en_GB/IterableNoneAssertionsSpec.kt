package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {

    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        getNonePair(),
        getNoneNullablePair(),
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
        private val noneFun : KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::none
        fun getNonePair() = noneFun.name to Companion::none

        private fun none(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant none a

        private val noneNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::none
        fun getNoneNullablePair() = noneNullableFun.name to Companion::noneNullable

        private fun noneNullable(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant none a


        private fun getContainsNotPair()
            = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant notTo contain entry a

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant notTo contain nullableEntry a
    }
}
