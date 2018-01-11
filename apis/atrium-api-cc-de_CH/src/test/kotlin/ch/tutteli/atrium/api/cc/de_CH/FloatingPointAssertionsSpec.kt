package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class FloatingPointAssertionsSpec : ch.tutteli.atrium.spec.assertions.FloatingPointAssertionsSpec(
    AssertionVerbFactory,
    toBeWithErrorToleranceFloatPair(),
    toBeWithErrorToleranceDoublePair(),
    toBeWithErrorToleranceBigDecimalPair(),
    toBeFloatPair(),
    toBeDoublePair()
) {
    companion object {

        private val toBeWithErrorToleranceFloatFun: KFunction3<AssertionPlant<Float>, Float, Float, AssertionPlant<Float>> = AssertionPlant<Float>::istMitFehlerToleranz
        fun toBeWithErrorToleranceFloatPair()
            = "${toBeWithErrorToleranceFloatFun.name} for Float" to Companion::toBeWithErrorToleranceFloat

        private fun toBeWithErrorToleranceFloat(plant: AssertionPlant<Float>, expected: Float, tolerance: Float)
            = plant.istMitFehlerToleranz(expected, tolerance)

        private val toBeWithErrorToleranceDoubleFun: KFunction3<AssertionPlant<Double>, Double, Double, AssertionPlant<Double>> = AssertionPlant<Double>::istMitFehlerToleranz
        fun toBeWithErrorToleranceDoublePair()
            = "${toBeWithErrorToleranceDoubleFun.name} for Double" to Companion::toBeWithErrorToleranceDouble

        private fun toBeWithErrorToleranceDouble(plant: AssertionPlant<Double>, expected: Double, tolerance: Double)
            = plant.istMitFehlerToleranz(expected, tolerance)

        private val toBeWithErrorToleranceBigDecimalFun: KFunction3<AssertionPlant<BigDecimal>, BigDecimal, BigDecimal, AssertionPlant<BigDecimal>> = AssertionPlant<BigDecimal>::istMitFehlerToleranz
        fun toBeWithErrorToleranceBigDecimalPair()
            = "${toBeWithErrorToleranceBigDecimalFun.name} for BigDecimal" to Companion::toBeWithErrorToleranceBigDecimal

        private fun toBeWithErrorToleranceBigDecimal(plant: AssertionPlant<BigDecimal>, expected: BigDecimal, tolerance: BigDecimal)
            = plant.istMitFehlerToleranz(expected, tolerance)


        private val toBeFloatFun: KFunction2<AssertionPlant<Float>, Float, AssertionPlant<Float>> = AssertionPlant<Float>::ist
        fun toBeFloatPair()
            = "${toBeFloatFun.name} for Float" to Companion::toBeFloat

        private fun toBeFloat(plant: AssertionPlant<Float>, expected: Float)
            = plant.ist(expected)

        private val toBeDoubleFun: KFunction2<AssertionPlant<Double>, Double, AssertionPlant<Double>> = AssertionPlant<Double>::ist
        fun toBeDoublePair()
            = "${toBeDoubleFun.name} for Double" to Companion::toBeDouble

        private fun toBeDouble(plant: AssertionPlant<Double>, expected: Double)
            = plant.ist(expected)
    }
}
