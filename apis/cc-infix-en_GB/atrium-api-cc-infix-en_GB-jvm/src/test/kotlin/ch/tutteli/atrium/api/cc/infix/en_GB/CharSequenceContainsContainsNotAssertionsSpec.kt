// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsContainsNotAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    "◆ ", "⚬ ", "▶ "
) {
    companion object {
        private val containsFun: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::contains
        fun getContainsPair() = "${containsFun.name} ${Values::class.simpleName}" to Companion::contains

        private fun contains(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant.asExpect().contains(a).asAssert()
            } else {
                plant.asExpect().contains(Values(a, *aX)).asAssert()
            }
        }

        private val containsNotFun: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNot

        private fun containsNot(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsNot(a).asAssert()
            } else {
                plant.asExpect().containsNot(Values(a, *aX)).asAssert()
            }
        }
    }

}
