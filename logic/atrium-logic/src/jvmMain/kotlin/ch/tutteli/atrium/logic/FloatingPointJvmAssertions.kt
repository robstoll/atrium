package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

/**
 * Collection of assertion functions and builders which are applicable to floating point number like types which are
 * specific to the JVM platform (currently only for [BigDecimal]).
 */
interface FloatingPointJvmAssertions {
    fun <T : BigDecimal> toBeWithErrorTolerance(
        container: AssertionContainer<T>,
        expected: BigDecimal,
        tolerance: BigDecimal
    ): Assertion
}
