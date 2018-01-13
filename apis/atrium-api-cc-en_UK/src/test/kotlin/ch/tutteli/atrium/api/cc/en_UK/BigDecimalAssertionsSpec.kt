package ch.tutteli.atrium.api.cc.en_UK

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
            = AssertionPlant<BigDecimal>::isNumericallyEqualTo.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant.isNumericallyEqualTo(expected)

        fun toBePair()
            = AssertionPlant<BigDecimal>::toBe.name to Companion::toBe

        private fun toBe(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant.toBe(expected)
    }
}
