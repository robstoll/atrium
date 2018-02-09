package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair()
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaelt(a, *aX)

        private val containsNotFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::enthaeltNicht
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>)
            = plant.enthaeltNicht(a, *aX)
    }
}
