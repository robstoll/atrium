//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

fun <T : BigDecimal> AssertionContainer<T>.isNumericallyEqualTo(expected: T): Assertion = _bigDecimalImpl.isNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isNotNumericallyEqualTo(expected: T): Assertion = _bigDecimalImpl.isNotNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion =
    _bigDecimalImpl.isEqualIncludingScale(this, expected, nameOfIsNumericallyEqualTo)

fun <T : BigDecimal> AssertionContainer<T>.isNotEqualIncludingScale(expected: T): Assertion = _bigDecimalImpl.isNotEqualIncludingScale(this, expected)
