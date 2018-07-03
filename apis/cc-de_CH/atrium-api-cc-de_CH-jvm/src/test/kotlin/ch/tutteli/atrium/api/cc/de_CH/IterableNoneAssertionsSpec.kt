package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {

    object PredicateSpec : ch.tutteli.atrium.spec.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        Assert<Iterable<Double>>::keiner.name to Assert<Iterable<Double>>::keiner,
        Assert<Iterable<Double?>>::keinerDerNullable.name to Assert<Iterable<Double?>>::keinerDerNullable,
        "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.spec.integration.IterableNoneAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )
    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair()
            = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit)
                = plant.enthaeltNicht.eintrag(a)

        private fun getContainsNotNullablePair() = "$containsNot nullable" to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?)
                = plant.enthaeltNicht.nullableEintrag(a)
    }
}
