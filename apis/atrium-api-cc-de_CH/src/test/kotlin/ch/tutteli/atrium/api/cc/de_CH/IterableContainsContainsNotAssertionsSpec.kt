package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair()
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaelt(a, *aX)

        private val containsNotFun: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaeltNicht
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaeltNicht(a, *aX)
    }
}
