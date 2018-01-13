package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionBigDecimalAssertions.IS_NUMERICALLY_EQUAL_TO
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

fun <T : BigDecimal> _isNumericallyEqual(plant: AssertionPlant<T>, expected: T)
    = BasicDescriptiveAssertion(IS_NUMERICALLY_EQUAL_TO, expected, { plant.subject.compareTo(expected) == 0 })
