package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.enthaeltNicht
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.enthaelt
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    IAssertionPlant<CharSequence>::enthaeltNicht.name to IAssertionPlant<CharSequence>::enthaeltNicht
) {
    companion object {
        private val containsFun: KFunction3<IAssertionPlant<CharSequence>, Any, Array<out Any>, IAssertionPlant<CharSequence>> = IAssertionPlant<CharSequence>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaelt(a, *aX)

    }
}
