// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [BigDecimal] type.
 */
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
