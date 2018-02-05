@file:JvmName("DeprecatedFloatingPointAssertions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

@Deprecated("use FloatingPointAssertions.toBeWithErrorTolerance instead, will be removed with 1.0.0", ReplaceWith("FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)"))
fun _toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    = FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

@Deprecated("use FloatingPointAssertions.toBeWithErrorTolerance instead, will be removed with 1.0.0", ReplaceWith("FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)"))
fun _toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    = FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)

@Deprecated("use FloatingPointAssertions.toBeWithErrorTolerance instead, will be removed with 1.0.0", ReplaceWith("FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)"))
fun <T : BigDecimal> _toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
    = FloatingPointAssertions.toBeWithErrorTolerance(plant, expected, tolerance)
