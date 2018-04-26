package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class BooleanAssertionsSpec : ch.tutteli.atrium.spec.integration.BooleanAssertionsSpec(
    AssertionVerbFactory,
    Assert<Boolean>::toBe.name to Companion::toBeTrue,
    Assert<Boolean>::toBe.name to Companion::toBeFalse
) {
    companion object {
        private fun toBeTrue(plant: Assert<Boolean>): Assert<Boolean>
            = plant toBe true

        private fun toBeFalse(plant: Assert<Boolean>): Assert<Boolean>
            = plant toBe false
    }

}
