package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import java.math.BigDecimal
import kotlin.reflect.KFunction3

class FloatingPointWithErrorToleranceExpectationsJvmSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceExpectationsJvmSpec(
        toBeWithErrorToleranceBigDecimalPair()
    ) {
    companion object {

        private val toBeWithErrorToleranceBigDecimalFun: KFunction3<Expect<BigDecimal>, BigDecimal, BigDecimal, Expect<BigDecimal>> =
            Expect<BigDecimal>::toBeWithErrorTolerance

        fun toBeWithErrorToleranceBigDecimalPair() =
            "${toBeWithErrorToleranceBigDecimalFun.name} for BigDecimal" to Companion::toBeWithErrorToleranceBigDecimal

        private fun toBeWithErrorToleranceBigDecimal(
            expect: Expect<BigDecimal>,
            expected: BigDecimal,
            tolerance: BigDecimal
        ) = expect.toBeWithErrorTolerance(expected, tolerance)
    }
}
