package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    AssertionPlant<Iterable<Double>>::enthaeltNicht.name to AssertionPlant<Iterable<Double>>::enthaeltNicht
) {
    companion object {
        private val containsFun: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaelt(a, *aX)
    }
}
