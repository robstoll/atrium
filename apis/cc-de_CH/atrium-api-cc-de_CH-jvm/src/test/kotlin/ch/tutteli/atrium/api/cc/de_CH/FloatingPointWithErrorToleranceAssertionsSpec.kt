package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
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

        private val toBeWithErrorToleranceFloatFun: KFunction3<Assert<Float>, Float, Float, Assert<Float>> = Assert<Float>::istMitFehlerToleranz
        fun toBeWithErrorToleranceFloatPair()
            = "${toBeWithErrorToleranceFloatFun.name} for Float" to Companion::toBeWithErrorToleranceFloat

        private fun toBeWithErrorToleranceFloat(plant: Assert<Float>, expected: Float, tolerance: Float)
            = plant.istMitFehlerToleranz(expected, tolerance)

        private val toBeWithErrorToleranceDoubleFun: KFunction3<Assert<Double>, Double, Double, Assert<Double>> = Assert<Double>::istMitFehlerToleranz
        fun toBeWithErrorToleranceDoublePair()
            = "${toBeWithErrorToleranceDoubleFun.name} for Double" to Companion::toBeWithErrorToleranceDouble

        private fun toBeWithErrorToleranceDouble(plant: Assert<Double>, expected: Double, tolerance: Double)
            = plant.istMitFehlerToleranz(expected, tolerance)

        private val toBeWithErrorToleranceBigDecimalFun: KFunction3<Assert<BigDecimal>, BigDecimal, BigDecimal, Assert<BigDecimal>> = Assert<BigDecimal>::istMitFehlerToleranz
        fun toBeWithErrorToleranceBigDecimalPair()
            = "${toBeWithErrorToleranceBigDecimalFun.name} for BigDecimal" to Companion::toBeWithErrorToleranceBigDecimal

        private fun toBeWithErrorToleranceBigDecimal(plant: Assert<BigDecimal>, expected: BigDecimal, tolerance: BigDecimal)
            = plant.istMitFehlerToleranz(expected, tolerance)
    }
}
