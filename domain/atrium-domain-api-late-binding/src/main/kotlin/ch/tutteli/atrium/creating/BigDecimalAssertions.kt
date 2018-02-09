package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import java.math.BigDecimal

/**
 * A dummy implementation of [IBigDecimalAssertions] which should be replaced by an actual implementation.
 */
object BigDecimalAssertions: IBigDecimalAssertions {
    override fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
        = throwUnsupportedOperationException()
    override fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
}
