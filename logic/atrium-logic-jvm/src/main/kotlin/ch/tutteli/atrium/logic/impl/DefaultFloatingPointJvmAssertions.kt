@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

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
