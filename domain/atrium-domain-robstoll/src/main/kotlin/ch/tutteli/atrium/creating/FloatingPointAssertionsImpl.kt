package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

/**
 * Robstoll's implementation of [FloatingPointAssertions].
 */
class FloatingPointAssertionsImpl: FloatingPointAssertions {

    override fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
        = _toBeWithErrorTolerance(plant, expected, tolerance)

    override fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
        = _toBeWithErrorTolerance(plant, expected, tolerance)

    override fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
        = _toBeWithErrorTolerance(plant, expected, tolerance)
}
