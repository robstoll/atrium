package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders.nameContainsNotValuesFun
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction2

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    nameContainsNotValuesFun() to Companion::containsNot,
    "▶ "
) {
    companion object {
        private val containsFun: KFunction2<IAssertionPlant<CharSequence>, Values<Any>, IAssertionPlant<CharSequence>> = IAssertionPlant<CharSequence>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: IAssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): IAssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, aX)
            }
        }

        private fun containsNot(plant: IAssertionPlant<CharSequence>, a: Any, aX: Array<out Any>): IAssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, aX)
            }
        }
    }

}
