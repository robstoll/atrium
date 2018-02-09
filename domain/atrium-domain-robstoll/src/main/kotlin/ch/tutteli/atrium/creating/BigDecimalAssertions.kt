package ch.tutteli.atrium.creating

import java.math.BigDecimal
/**
 * Robstoll's implementation of [IBigDecimalAssertions].
 */
object BigDecimalAssertions: IBigDecimalAssertions {
    override fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = _isNumericallyEqualTo(plant, expected)

    override fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = _isNotNumericallyEqualTo(plant, expected)

    override fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String)
        = _isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

    override fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
        = _isNotEqualIncludingScale(plant, expected)
}
