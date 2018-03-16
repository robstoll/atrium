package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.robstoll.lib.creating._toBeWithErrorTolerance
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
