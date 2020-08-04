package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with [Float] as type.
 */
interface FloatAssertions {
    fun toBeWithErrorTolerance(container: AssertionContainer<Float>, expected: Float, tolerance: Float): Assertion
}
