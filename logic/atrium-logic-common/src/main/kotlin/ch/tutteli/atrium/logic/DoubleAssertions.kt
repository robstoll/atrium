package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with [Double] as type.
 */
interface DoubleAssertions {
    fun toBeWithErrorTolerance(container: AssertionContainer<Double>, expected: Double, tolerance: Double): Assertion
}
