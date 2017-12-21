package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    AssertionPlant<Iterable<Double>>::containsNot.name to AssertionPlant<Iterable<Double>>::containsNot
) {
    companion object {
        private val containsFun: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.contains(a, *aX)
    }
}
