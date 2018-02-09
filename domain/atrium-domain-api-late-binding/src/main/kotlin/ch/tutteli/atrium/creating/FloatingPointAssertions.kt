package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

/**
 * A dummy implementation of [IFloatingPointAssertions] which should be replaced by an actual implementation.
 */
object FloatingPointAssertions: IFloatingPointAssertions {
    override fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
        = throwUnsupportedOperationException()
    override fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
        = throwUnsupportedOperationException()
    override fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
        = throwUnsupportedOperationException()
}
