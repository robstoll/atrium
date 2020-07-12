package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with a floating point number like
 * type (currently [Float] and [Double]).
 */
interface FloatingPointAssertions {
    fun toBeWithErrorTolerance(container: AssertionContainer<Float>, expected: Float, tolerance: Float): Assertion
    fun toBeWithErrorTolerance(container: AssertionContainer<Double>, expected: Double, tolerance: Double): Assertion
}
