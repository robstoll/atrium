//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [BigDecimal] type.
 */
//TODO 1.3.0 deprecate
interface BigDecimalAssertions {
    fun <T : BigDecimal> isNumericallyEqualTo(container: AssertionContainer<T>, expected: T): Assertion
    fun <T : BigDecimal> isNotNumericallyEqualTo(container: AssertionContainer<T>, expected: T): Assertion
    fun <T : BigDecimal> isEqualIncludingScale(
        container: AssertionContainer<T>,
        expected: T,
        nameOfIsNumericallyEqualTo: String
    ): Assertion

    fun <T : BigDecimal> isNotEqualIncludingScale(container: AssertionContainer<T>, expected: T): Assertion
}
