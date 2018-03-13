package ch.tutteli.atrium.creating

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal
import java.util.*

/**
 * The access point to an implementation of [FloatingPointAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val floatingPointAssertions by lazy { SingleServiceLoader.load(FloatingPointAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double],
 * [BigDecimal]), which an implementation of the domain of Atrium has to provide.
 */
interface FloatingPointAssertions {
    fun toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    fun toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
}
