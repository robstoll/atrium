package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BigDecimalAssertions
import ch.tutteli.atrium.robstoll.lib.creating._isEqualIncludingScale
import ch.tutteli.atrium.robstoll.lib.creating._isNotEqualIncludingScale
import ch.tutteli.atrium.robstoll.lib.creating._isNotNumericallyEqualTo
import ch.tutteli.atrium.robstoll.lib.creating._isNumericallyEqualTo
import java.math.BigDecimal

/**
 * Robstoll's implementation of [BigDecimalAssertions].
 */
class BigDecimalAssertionsImpl: BigDecimalAssertions {

    override fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = _isNumericallyEqualTo(plant, expected)

    override fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = _isNotNumericallyEqualTo(plant, expected)

    override fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String)
        = _isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

    override fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
        = _isNotEqualIncludingScale(plant, expected)
}
