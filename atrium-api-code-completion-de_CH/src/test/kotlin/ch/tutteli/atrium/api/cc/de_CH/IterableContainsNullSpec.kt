package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsNullSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
    AssertionVerbFactory,
    getContainsPair(),
    IAssertionPlant<Iterable<Double?>>::enthaeltNicht.name to IAssertionPlant<Iterable<Double?>>::enthaeltNicht
) {
    companion object {
        private val containsFun: KFunction3<IAssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, IAssertionPlant<Iterable<Double?>>> = IAssertionPlant<Iterable<Double?>>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.enthaelt(a, *aX)
    }
}
