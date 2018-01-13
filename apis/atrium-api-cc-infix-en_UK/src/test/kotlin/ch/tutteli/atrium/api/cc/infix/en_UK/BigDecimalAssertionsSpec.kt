package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal


class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    isNumericallyEqualToPair(),
    isNotNumericallyEqualToPair(),
    toBePair(),
    notToBePair(),
    Companion::notToBeAny
) {
    companion object {
        fun isNumericallyEqualToPair()
            = AssertionPlant<BigDecimal>::isNumericallyEqualTo.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant isNumericallyEqualTo expected

        fun isNotNumericallyEqualToPair()
            = AssertionPlant<BigDecimal>::isNotNumericallyEqualTo.name to Companion::isNotNumericallyEqualTo

        private fun isNotNumericallyEqualTo(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant isNotNumericallyEqualTo expected


        fun toBePair()
            = AssertionPlant<BigDecimal>::toBe.name to Companion::toBe

        private fun toBe(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant toBe expected

        @Suppress("DEPRECATION")
        fun notToBePair()
            = AssertionPlant<BigDecimal>::notToBe.name to Companion::notToBe

        @Suppress("DEPRECATION")
        private fun notToBe(plant: AssertionPlant<BigDecimal>, expected: BigDecimal): Nothing
            = plant notToBe expected

        private fun notToBeAny(plant: AssertionPlant<Any>, expected: Any)
            = plant notToBe expected

    }
}
