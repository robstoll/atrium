@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import java.math.BigDecimal
import kotlin.reflect.KFunction3

class FloatingPointWithErrorToleranceAssertionsSpec : ch.tutteli.atrium.spec.integration.FloatingPointWithErrorToleranceAssertionsSpec(
    AssertionVerbFactory,
    toBeWithErrorToleranceFloatPair(),
    toBeWithErrorToleranceDoublePair(),
    toBeWithErrorToleranceBigDecimalPair()

) {
    companion object {

        private val toBeWithErrorToleranceFloatFun: KFunction3<Assert<Float>, Float, Float, Assert<Float>> = Assert<Float>::toBeWithErrorTolerance
        fun toBeWithErrorToleranceFloatPair()
            = "${toBeWithErrorToleranceFloatFun.name} for Float" to Companion::toBeWithErrorToleranceFloat

        private fun toBeWithErrorToleranceFloat(plant: Assert<Float>, expected: Float, tolerance: Float)
            = plant.toBeWithErrorTolerance(expected, tolerance)

        private val toBeWithErrorToleranceDoubleFun: KFunction3<Assert<Double>, Double, Double, Assert<Double>> = Assert<Double>::toBeWithErrorTolerance
        fun toBeWithErrorToleranceDoublePair()
            = "${toBeWithErrorToleranceDoubleFun.name} for Double" to Companion::toBeWithErrorToleranceDouble

        private fun toBeWithErrorToleranceDouble(plant: Assert<Double>, expected: Double, tolerance: Double)
            = plant.toBeWithErrorTolerance(expected, tolerance)

        private val toBeWithErrorToleranceBigDecimalFun: KFunction3<Assert<BigDecimal>, BigDecimal, BigDecimal, Assert<BigDecimal>> = Assert<BigDecimal>::toBeWithErrorTolerance
        fun toBeWithErrorToleranceBigDecimalPair()
            = "${toBeWithErrorToleranceBigDecimalFun.name} for BigDecimal" to Companion::toBeWithErrorToleranceBigDecimal

        private fun toBeWithErrorToleranceBigDecimal(plant: Assert<BigDecimal>, expected: BigDecimal, tolerance: BigDecimal)
            = plant.toBeWithErrorTolerance(expected, tolerance)
    }
}
