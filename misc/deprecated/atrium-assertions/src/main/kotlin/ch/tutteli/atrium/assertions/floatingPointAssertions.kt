// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import java.math.BigDecimal

@Deprecated("Use AssertImpl.floatingPoint.toBeWithErrorTolerance; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun _toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    = AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)

@Deprecated("Use AssertImpl.floatingPoint.toBeWithErrorTolerance; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun _toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    = AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)

@Deprecated("Use AssertImpl.floatingPoint.toBeWithErrorTolerance; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : BigDecimal> _toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
    = AssertImpl.floatingPoint.toBeWithErrorTolerance(plant, expected, tolerance)
