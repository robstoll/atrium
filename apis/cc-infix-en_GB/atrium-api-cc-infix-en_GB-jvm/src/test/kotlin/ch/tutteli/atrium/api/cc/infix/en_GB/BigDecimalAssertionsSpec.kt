@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal
import kotlin.reflect.KFunction2

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
        private val toBeFun : KFunction2<Assert<BigDecimal>, BigDecimal, Assert<BigDecimal>> =  Assert<BigDecimal>::toBe

        @Suppress("DEPRECATION")
        fun toBePair()
            = toBeFun.name to Companion::toBe

        @Suppress("DEPRECATION")
        private fun toBe(plant: Assert<BigDecimal>, a: BigDecimal): Nothing
            = plant toBe a

        private fun toBeAny(plant: Assert<Any>, expected: Any)
            = plant toBe expected

        @Suppress("DEPRECATION")
        private val notToBeFun : KFunction2<Assert<BigDecimal>, BigDecimal, Assert<BigDecimal>> =  Assert<BigDecimal>::notToBe

        @Suppress("DEPRECATION")
        fun notToBePair()
            = notToBeFun.name to Companion::notToBe

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
