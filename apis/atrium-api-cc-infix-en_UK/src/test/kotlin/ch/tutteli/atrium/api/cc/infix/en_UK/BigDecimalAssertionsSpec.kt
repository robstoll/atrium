@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.integration.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    toBePair(),
    Companion::toBeAny,
    notToBePair(),
    Companion::notToBeAny,
    isNumericallyEqualToPair(),
    isNotNumericallyEqualToPair(),
    isEqualIncludingScalePair(),
    isNotEqualIncludingScalePair()
) {
    companion object {
        @Suppress("DEPRECATION")
        fun toBePair()
            = Assert<BigDecimal>::toBe.name to Companion::toBe

        @Suppress("DEPRECATION")
        private fun toBe(plant: Assert<BigDecimal>, a: BigDecimal): Nothing
            = plant toBe a

        private fun toBeAny(plant: Assert<Any>, expected: Any)
            = plant toBe expected

        @Suppress("DEPRECATION")
        fun notToBePair()
            = Assert<BigDecimal>::notToBe.name to Companion::notToBe

        @Suppress("DEPRECATION")
        private fun notToBe(plant: Assert<BigDecimal>, expected: BigDecimal): Nothing
            = plant notToBe expected

        private fun notToBeAny(plant: Assert<Any>, expected: Any)
            = plant notToBe expected

        fun isNumericallyEqualToPair()
            = Assert<BigDecimal>::isNumericallyEqualTo.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isNumericallyEqualTo expected

        fun isNotNumericallyEqualToPair()
            = Assert<BigDecimal>::isNotNumericallyEqualTo.name to Companion::isNotNumericallyEqualTo

        private fun isNotNumericallyEqualTo(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isNotNumericallyEqualTo expected

        fun isEqualIncludingScalePair()
            = Assert<BigDecimal>::isEqualIncludingScale.name to Companion::isEqualIncludingScale

        private fun isEqualIncludingScale(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isEqualIncludingScale expected

        fun isNotEqualIncludingScalePair()
            = Assert<BigDecimal>::isNotEqualIncludingScale.name to Companion::isNotEqualIncludingScale

        private fun isNotEqualIncludingScale(plant: Assert<BigDecimal>, expected: BigDecimal)
            = plant isNotEqualIncludingScale expected
    }
}
