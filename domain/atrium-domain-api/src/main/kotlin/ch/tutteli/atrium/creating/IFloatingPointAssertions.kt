package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double],
 * [BigDecimal]), which an implementation of the domain of Atrium has to provide.
 */
interface IFloatingPointAssertions {
    fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
}
