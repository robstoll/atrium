package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal
import java.util.*

/**
 * The access point to an implementation of [BigDecimalAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val bigDecimalAssertions by lazy { SingleServiceLoader.load(BigDecimalAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [BigDecimal],
 * which an implementation of the domain of Atrium has to provide.
 */
interface BigDecimalAssertions {
    fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : BigDecimal> isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    fun <T : BigDecimal> isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T): Assertion
}
