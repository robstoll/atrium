package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

@Deprecated("use AssertImpl.bigDecimal.isNumericallyEqualTo, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.bigDecimal.isNumericallyEqualTo(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.bigDecimal.isNumericallyEqualTo(plant, expected)

@Deprecated("use AssertImpl.bigDecimal.isNotNumericallyEqualTo, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.bigDecimal.isNotNumericallyEqualTo(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.bigDecimal.isNotNumericallyEqualTo(plant, expected)

@Deprecated("use AssertImpl.bigDecimal.isEqualIncludingScale, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.bigDecimal.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : BigDecimal> _isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    = AssertImpl.bigDecimal.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

@Deprecated("use AssertImpl.bigDecimal.isNotEqualIncludingScale, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.bigDecimal.isNotEqualIncludingScale(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : BigDecimal> _isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.bigDecimal.isNotEqualIncludingScale(plant, expected)
