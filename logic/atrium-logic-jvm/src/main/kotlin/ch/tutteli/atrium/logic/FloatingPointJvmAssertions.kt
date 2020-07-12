package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

interface FloatingPointJvmAssertions {
    fun <T : BigDecimal> toBeWithErrorTolerance(
        container: AssertionContainer<T>,
        expected: BigDecimal,
        tolerance: BigDecimal
    ): Assertion
}
