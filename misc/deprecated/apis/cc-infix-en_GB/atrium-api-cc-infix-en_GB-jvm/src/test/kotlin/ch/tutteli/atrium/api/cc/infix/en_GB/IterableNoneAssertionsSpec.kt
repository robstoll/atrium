// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.api.infix.en_GB.none
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.migration.asSubExpect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2
import ch.tutteli.atrium.api.infix.en_GB.o

//TODO remove with 1.0.0, no need to migrate to Spek 2
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
            = plant.asExpect().none(asSubExpect(a)).asAssert()

        private val noneNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> = Assert<Iterable<Double?>>::none
        fun getNoneNullablePair() = noneNullableFun.name to Companion::noneNullable

        private fun noneNullable(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.asExpect().none(asSubExpect(a)).asAssert()


        private fun getContainsNotPair()
            = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
            = plant.asExpect().containsNot(o) entry a

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
            = plant.asExpect().containsNot(o) entry a
    }
}
