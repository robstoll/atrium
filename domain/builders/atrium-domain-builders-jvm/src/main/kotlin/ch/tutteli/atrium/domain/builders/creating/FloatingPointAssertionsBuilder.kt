@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.domain.creating.floatingPointAssertions
import java.math.BigDecimal
import ch.tutteli.atrium.core.polyfills.loadSingleService

/**
 * Delegates inter alia to the implementation of [FloatingPointAssertions].
 * In detail, it implements [FloatingPointAssertions] by delegating to [floatingPointAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object FloatingPointAssertionsBuilder : FloatingPointAssertions {

    override inline fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

    override inline fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

    override inline fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T)
        = floatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)
}
