@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BigDecimalAssertions
import ch.tutteli.atrium.creating.bigDecimalAssertions
import java.math.BigDecimal

object BigDecimalAssertionsBuilder : BigDecimalAssertions {
    override inline fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = bigDecimalAssertions.isNumericallyEqualTo(plant, expected)

    override inline fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
        = bigDecimalAssertions.isNotNumericallyEqualTo(plant, expected)

    override inline fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String)
        = bigDecimalAssertions.isEqualIncludingScale(plant, expected, nameOfIsNumericallyEqualTo)

    override inline fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
        = bigDecimalAssertions.isNotEqualIncludingScale(plant, expected)
}
