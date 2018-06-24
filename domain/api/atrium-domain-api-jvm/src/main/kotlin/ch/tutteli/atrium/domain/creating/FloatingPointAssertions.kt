package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double],
 * [BigDecimal]), which an implementation of the domain of Atrium has to provide.
 */
actual interface FloatingPointAssertions: FloatingPointAssertionsCommon {
    fun <T : BigDecimal> toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion
}
