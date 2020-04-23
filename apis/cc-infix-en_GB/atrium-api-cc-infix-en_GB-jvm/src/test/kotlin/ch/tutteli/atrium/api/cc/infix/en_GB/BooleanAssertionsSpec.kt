// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class BooleanAssertionsSpec : ch.tutteli.atrium.spec.integration.BooleanAssertionsSpec(
    AssertionVerbFactory,
    toBeName() to Companion::toBeTrue,
    toBeName() to Companion::toBeFalse
) {
    companion object {
        fun toBeName(): String{
            val f : KFunction2<Assert<Boolean>, Boolean, Assert<Boolean>> = Assert<Boolean>::toBe
            return f.name
        }

        private fun toBeTrue(plant: Assert<Boolean>): Assert<Boolean>
            = plant toBe true

        private fun toBeFalse(plant: Assert<Boolean>): Assert<Boolean>
            = plant toBe false
    }

}
