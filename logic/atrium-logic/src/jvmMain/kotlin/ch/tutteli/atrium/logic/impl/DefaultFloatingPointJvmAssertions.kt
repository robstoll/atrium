//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.FloatingPointJvmAssertions
import java.math.BigDecimal

class DefaultFloatingPointJvmAssertions : FloatingPointJvmAssertions {
    override fun <T : BigDecimal> toBeWithErrorTolerance(
        container: AssertionContainer<T>,
        expected: BigDecimal,
        tolerance: BigDecimal
    ): Assertion {
        val absDiff = { subject: BigDecimal -> (subject - expected).abs() }
        return toBeWithErrorTolerance(container, expected, tolerance, absDiff) { subject ->
            listOf(createToBeWithErrorToleranceExplained(subject, expected, absDiff, tolerance))
        }
    }
}
