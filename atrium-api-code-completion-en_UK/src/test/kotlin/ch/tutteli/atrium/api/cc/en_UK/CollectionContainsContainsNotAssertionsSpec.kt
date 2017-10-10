package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction3

class CollectionContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    IAssertionPlant<Collection<Double>>::containsNot.name to IAssertionPlant<Collection<Double>>::containsNot
) {
    companion object {
        private val containsFun: KFunction3<IAssertionPlant<Collection<Double>>, Double, Array<out Double>, IAssertionPlant<Collection<Double>>> = IAssertionPlant<Collection<Double>>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<Collection<Double>>, a: Double, aX: Array<out Double>)
            = plant.contains(a, *aX)

    }
}
