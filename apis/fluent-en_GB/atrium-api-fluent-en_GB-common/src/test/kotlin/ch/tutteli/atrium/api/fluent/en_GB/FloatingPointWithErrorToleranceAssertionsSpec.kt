package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction3

class FloatingPointWithErrorToleranceAssertionsSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceAssertionsSpec(
        AssertionVerbFactory,
        toBeWithErrorToleranceFloatPair(),
        toBeWithErrorToleranceDoublePair()

    ) {
    companion object {

        private val toBeWithErrorToleranceFloatFun: KFunction3<Expect<Float>, Float, Float, Expect<Float>> =
            Expect<Float>::toBeWithErrorTolerance

        fun toBeWithErrorToleranceFloatPair() =
            "${toBeWithErrorToleranceFloatFun.name} for Float" to Companion::toBeWithErrorToleranceFloat

        private fun toBeWithErrorToleranceFloat(plant: Expect<Float>, expected: Float, tolerance: Float) =
            plant.toBeWithErrorTolerance(expected, tolerance)

        private val toBeWithErrorToleranceDoubleFun: KFunction3<Expect<Double>, Double, Double, Expect<Double>> =
            Expect<Double>::toBeWithErrorTolerance

        fun toBeWithErrorToleranceDoublePair() =
            "${toBeWithErrorToleranceDoubleFun.name} for Double" to Companion::toBeWithErrorToleranceDouble

        private fun toBeWithErrorToleranceDouble(plant: Expect<Double>, expected: Double, tolerance: Double) =
            plant.toBeWithErrorTolerance(expected, tolerance)
    }
}
