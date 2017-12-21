package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    AssertionPlant<CharSequence>::enthaeltNicht.name to AssertionPlant<CharSequence>::enthaeltNicht,
    "▶ "
) {
    companion object {
        private val containsFun: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaelt(a, *aX)

    }
}
