package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    isNumericallyEqualToPair(),
    toBePair()
) {
    companion object {
        fun isNumericallyEqualToPair()
            = AssertionPlant<BigDecimal>::istNumerischGleichWie.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant.istNumerischGleichWie(expected)

        fun toBePair()
            = AssertionPlant<BigDecimal>::ist.name to Companion::toBe

        private fun toBe(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant.ist(expected)
    }
}
