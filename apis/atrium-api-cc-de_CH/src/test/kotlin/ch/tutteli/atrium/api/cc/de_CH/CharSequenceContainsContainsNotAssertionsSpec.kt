package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    ">> "
) {
    companion object : CharSequenceContainsSpecBase() {
        private val containsFun: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaelt(a, *aX)

        private val containsNotFun: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::enthaeltNicht
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaeltNicht(a, *aX)
    }
}
