package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsContainsNotAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    "* ", "- ", ">> "
) {
    companion object : CharSequenceContainsSpecBase() {
        private val containsFun: KFunction3<Assert<CharSequence>, Any, Array<out Any>, Assert<CharSequence>> = Assert<CharSequence>::enthaelt
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaelt(a, *aX)

        private val containsNotFun: KFunction3<Assert<CharSequence>, Any, Array<out Any>, Assert<CharSequence>> = Assert<CharSequence>::enthaeltNicht
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>)
            = plant.enthaeltNicht(a, *aX)
    }
}
