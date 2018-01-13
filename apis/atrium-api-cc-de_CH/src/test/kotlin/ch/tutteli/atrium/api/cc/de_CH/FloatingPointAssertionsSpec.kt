package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import java.math.BigDecimal
import kotlin.reflect.KFunction3

class FloatingPointAssertionsSpec : Spek({
    include(object : ch.tutteli.atrium.spec.assertions.FloatingPointWithErrorToleranceAssertionsSpec(
        AssertionVerbFactory,
        toBeWithErrorToleranceFloatPair(),
        toBeWithErrorToleranceDoublePair(),
        toBeWithErrorToleranceBigDecimalPair()

    ) {})
}) {
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
    }
}
