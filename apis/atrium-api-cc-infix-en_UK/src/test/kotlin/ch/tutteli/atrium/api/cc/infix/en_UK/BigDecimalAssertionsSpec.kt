package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
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
            = Assert<BigDecimal>::isNumericallyEqualTo.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isNumericallyEqualTo expected

        fun isNotNumericallyEqualToPair()
            = Assert<BigDecimal>::isNotNumericallyEqualTo.name to Companion::isNotNumericallyEqualTo

        private fun isNotNumericallyEqualTo(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isNotNumericallyEqualTo expected


        fun toBePair()
            = Assert<BigDecimal>::toBe.name to Companion::toBe

        private fun toBe(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant toBe expected

        @Suppress("DEPRECATION")
        fun notToBePair()
            = Assert<BigDecimal>::notToBe.name to Companion::notToBe

        @Suppress("DEPRECATION")
        private fun notToBe(plant: Assert<BigDecimal>, expected: BigDecimal): Nothing
            = plant notToBe expected

        private fun notToBeAny(plant: Assert<Any>, expected: Any)
            = plant notToBe expected

    }
}
