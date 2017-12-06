package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction2

class IterableContainsNullSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair()
) {
    companion object {
        private val containsFun: KFunction2<IAssertionPlant<Iterable<Double?>>, Values<Double?>, IAssertionPlant<Iterable<Double?>>> = IAssertionPlant<Iterable<Double?>>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant contains Values(a, aX)

        private val containsNotFun: KFunction2<IAssertionPlant<Iterable<Double?>>, Values<Double?>, IAssertionPlant<Iterable<Double?>>> = IAssertionPlant<Iterable<Double?>>::containsNot
        fun getContainsNotPair() = containsNotFun.name to Companion::containsNot

        private fun containsNot(plant: IAssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant containsNot Values(a, aX)


    }
}
