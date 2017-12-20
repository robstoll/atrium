package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders.nameContainsNotValuesFun
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction2

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    nameContainsNotValuesFun() to Companion::containsNot,
    "▶ "
) {
    companion object {
        private val containsFun: KFunction2<AssertionPlant<CharSequence>, Values<Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::contains
        fun getContainsPair() = "${containsFun.name} ${Values::class.simpleName}" to Companion::contains

        private fun contains(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, *aX)
            }
        }

        private fun containsNot(plant: AssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }
    }

}
