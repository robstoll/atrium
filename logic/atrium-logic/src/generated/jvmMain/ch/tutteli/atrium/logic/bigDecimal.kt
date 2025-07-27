// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultBigDecimalAssertions

fun <T : BigDecimal> AssertionContainer<T>.isNumericallyEqualTo(expected: T): Assertion = impl.isNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isNotNumericallyEqualTo(expected: T): Assertion = impl.isNotNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion =
    impl.isEqualIncludingScale(this, expected, nameOfIsNumericallyEqualTo)

fun <T : BigDecimal> AssertionContainer<T>.isNotEqualIncludingScale(expected: T): Assertion = impl.isNotEqualIncludingScale(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: BigDecimalAssertions
    get() = getImpl(BigDecimalAssertions::class) { DefaultBigDecimalAssertions() }
