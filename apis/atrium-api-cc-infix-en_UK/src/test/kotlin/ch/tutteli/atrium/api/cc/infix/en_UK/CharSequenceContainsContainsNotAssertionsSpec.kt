package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction2

class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    "▶ "
) {
    companion object {
        private val containsFun: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::contains
        fun getContainsPair() = "${containsFun.name} ${Values::class.simpleName}" to Companion::contains

        private fun contains(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, *aX)
            }
        }

        private val containsNotFun: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNot

        private fun containsNot(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }
    }

}
