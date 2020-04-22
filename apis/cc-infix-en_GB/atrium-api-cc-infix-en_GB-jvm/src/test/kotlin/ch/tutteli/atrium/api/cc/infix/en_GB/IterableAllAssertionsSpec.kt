// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableAllAssertionsSpec: Spek({
    include(PredicateSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableAllAssertionsSpec(
        AssertionVerbFactory,
        getAnyPair(),
        getAnyNullablePair(),
        "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
    )

    companion object {
        private val anyFun: KFunction2<Assert<Iterable<Double>>, Assert<Double>.() -> Unit, Assert<Iterable<Double>>> =
            Assert<Iterable<Double>>::any

        fun getAnyPair() = anyFun.name to Companion::all

        private fun all(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit) = plant all a

        private val anyNullableFun: KFunction2<Assert<Iterable<Double?>>, (Assert<Double>.() -> Unit)?, Assert<Iterable<Double?>>> =
            Assert<Iterable<Double?>>::any

        fun getAnyNullablePair() = anyNullableFun.name to Companion::allNullable

        private fun allNullable(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?) =
            plant all a
    }
}
