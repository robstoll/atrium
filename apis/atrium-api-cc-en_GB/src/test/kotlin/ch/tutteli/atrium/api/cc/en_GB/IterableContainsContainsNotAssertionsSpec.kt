package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair()
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: Assert<Iterable<Double>>, a: Any, aX: Array<out Any>)
            = plant.contains(a, *aX)

        private val containsNotFun: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<Iterable<Double>>, a: Any, aX: Array<out Any>)
            = plant.containsNot(a, *aX)
    }
}
