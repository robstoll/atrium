@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import java.math.BigDecimal

object FloatingPointAssertionsBuilder : FloatingPointAssertions{
    override inline fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

    override inline fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

    override inline fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)
}
