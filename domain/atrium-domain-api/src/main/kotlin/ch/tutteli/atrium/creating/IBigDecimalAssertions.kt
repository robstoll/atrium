package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

/**
 * Defines the minimum set of assertion functions and builders applicable to [BigDecimal] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface IBigDecimalAssertions {
    fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T): Assertion
}
