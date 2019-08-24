package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import java.math.BigDecimal
import kotlin.reflect.KFunction3

class FloatingPointWithErrorToleranceAssertionsJvmSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceAssertionsJvmSpec(
        toBeWithErrorToleranceBigDecimalPair()
    ) {
    companion object {

        private val toBeWithErrorToleranceBigDecimalFun: KFunction3<Expect<BigDecimal>, BigDecimal, BigDecimal, Expect<BigDecimal>> =
            Expect<BigDecimal>::toBeWithErrorTolerance

        fun toBeWithErrorToleranceBigDecimalPair() =
            "${toBeWithErrorToleranceBigDecimalFun.name} for BigDecimal" to Companion::toBeWithErrorToleranceBigDecimal

        private fun toBeWithErrorToleranceBigDecimal(
            plant: Expect<BigDecimal>,
            expected: BigDecimal,
            tolerance: BigDecimal
        ) = plant.toBeWithErrorTolerance(expected, tolerance)
    }
}
