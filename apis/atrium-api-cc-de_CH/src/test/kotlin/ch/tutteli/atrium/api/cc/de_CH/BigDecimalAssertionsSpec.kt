package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal
import kotlin.reflect.KFunction2

class BigDecimalAssertionsSpec : ch.tutteli.atrium.spec.assertions.BigDecimalAssertionsSpec(
    AssertionVerbFactory,
    isNumericallyEqualToPair()
) {
    companion object {

        private val isNumericallyEqualToFun: KFunction2<AssertionPlant<BigDecimal>, BigDecimal, AssertionPlant<BigDecimal>> = AssertionPlant<BigDecimal>::istNumerischGleichWie
        fun isNumericallyEqualToPair()
            = isNumericallyEqualToFun.name to Companion::isNumericallyEqualTo

        private fun isNumericallyEqualTo(plant: AssertionPlant<BigDecimal>, expected: BigDecimal)
            = plant.istNumerischGleichWie(expected)
    }
}
