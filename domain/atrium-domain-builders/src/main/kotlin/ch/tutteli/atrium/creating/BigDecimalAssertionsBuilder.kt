@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import java.math.BigDecimal

object BigDecimalAssertionsBuilder : IBigDecimalAssertions {
    override inline fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = BigDecimalAssertions.isNumericallyEqualTo(plant, expected)

    override inline fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = BigDecimalAssertions.isNotNumericallyEqualTo(plant, expected)

    override inline fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String)
        = BigDecimalAssertions.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

    override inline fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
        = BigDecimalAssertions.isNotEqualIncludingScale(plant, expected)
}
