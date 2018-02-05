package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BigDecimalAssertions
import java.math.BigDecimal

@Deprecated("use BigDecimalAssertions.isNumericallyEqualTo instead, will be removed with 1.0.0", ReplaceWith("BigDecimalAssertions.isNumericallyEqualTo(plant, expected)"))
fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
   = BigDecimalAssertions.isNumericallyEqualTo(plant, expected)

@Deprecated("use BigDecimalAssertions.isNotNumericallyEqualTo instead, will be removed with 1.0.0", ReplaceWith("BigDecimalAssertions.isNotNumericallyEqualTo(plant, expected)"))
fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = BigDecimalAssertions.isNotNumericallyEqualTo(plant, expected)

@Deprecated("use BigDecimalAssertions.isEqualIncludingScale instead, will be removed with 1.0.0", ReplaceWith("BigDecimalAssertions.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)"))
fun <T : BigDecimal> _isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    = BigDecimalAssertions.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

@Deprecated("use BigDecimalAssertions.isNotEqualIncludingScale instead, will be removed with 1.0.0", ReplaceWith("BigDecimalAssertions.isNotEqualIncludingScale(plant, expected)"))
fun <T : BigDecimal> _isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    = BigDecimalAssertions.isNotEqualIncludingScale(plant, expected)
