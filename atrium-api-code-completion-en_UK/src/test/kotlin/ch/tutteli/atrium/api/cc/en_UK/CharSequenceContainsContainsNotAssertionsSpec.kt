package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    IAssertionPlant<CharSequence>::containsNot.name to IAssertionPlant<CharSequence>::containsNot,
    "▶ "
) {
    companion object {
        private val containsFun: KFunction3<IAssertionPlant<CharSequence>, Any, Array<out Any>, IAssertionPlant<CharSequence>> = IAssertionPlant<CharSequence>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.contains(a, *aX)

    }
}
